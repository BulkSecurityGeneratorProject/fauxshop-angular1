package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Products;
import com.mycompany.myapp.domain.ProductsDescription;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.ProductsDescriptionService;
import com.mycompany.myapp.service.ProductsService;
import com.mycompany.myapp.service.dto.CartDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class CartResource {

    private final Logger log = LoggerFactory.getLogger(CartResource.class);

    private final CartService cartService;
    private final ProductsService productsService;
    private final ProductsDescriptionService productsDescriptionService;

    public CartResource(CartService cartService, ProductsService productsService, ProductsDescriptionService productsDescriptionService) {
        this.cartService = cartService;
        this.productsService = productsService;
        this.productsDescriptionService = productsDescriptionService;
    }

    /**
     * GET  /cart/{id} : return list of cart records for the given user id
     *
     * @param id the user id
     * @return the ResponseEntity with status 200 (OK) and the list of Cart records in the body, or status 500 (Internal Server Error)
     */
    @GetMapping("/cart/{id}")
    @Timed
    public ResponseEntity<List<CartDTO>> getCartById(@PathVariable("id") Long id) {
        List<CartDTO> cartRecords = new ArrayList<>();
        Optional<List<Cart>> cart = cartService.findAllById(id);

        if (cart.isPresent()) {
            for (Cart cartItem : cart.get()) {
                CartDTO cartRecord;
                Products product = productsService.getProductsByProductsId(cartItem.getProductsId()).orElse(null);
                ProductsDescription productsDescription = productsDescriptionService.getProductsDescriptionByProductsId(cartItem.getProductsId()).orElse(null);
                cartRecord = new CartDTO(cartItem, product, productsDescription);
                cartRecords.add(cartRecord);
            }
            return new ResponseEntity<List<CartDTO>>(cartRecords, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST  /cart/{id}/{productsId}/{cartItemQuantity} : saves the given cart record
     *
     * @param id the user id
     * @return the ResponseEntity with status 200 (OK) and the Cart record that was saved, or status 500 (Internal Server Error), or status 400 (Bad Request) if the product does not exist
     */
    @PostMapping("/cart/{id}/{productsId}/{cartItemQuantity}")
    @Timed
    public ResponseEntity<Cart> addToCart(@PathVariable("id") Long id, @PathVariable("productsId") Long productsId, @PathVariable("cartItemQuantity") Integer cartItemQuantity) {
        Cart cartRecord = new Cart();
        Optional<Products> productOptional = productsService.getProductsByProductsId(productsId);

        if (productOptional.isPresent()){
            cartRecord.setId(id);
            cartRecord.setProductsId(productsId);
            cartRecord.setCartItemQuantity(cartItemQuantity);
            cartRecord.setCartItemTotalPrice(productOptional.get().getProductsPrice());

            Cart cartRecordToPersist = examineExistingCartRecords(cartRecord, cartItemQuantity);

            try {
                return new ResponseEntity<Cart>(cartService.save(cartRecordToPersist), HttpStatus.OK);
            } catch( Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Cart examineExistingCartRecords(Cart cartRecord, Integer cartItemQuantity) {
        Optional<List<Cart>> existingCartRecords = cartService.findAllById(cartRecord.getId());
        if (existingCartRecords.isPresent()) {
            for (Cart existingCartRecord : existingCartRecords.get()) {
                if (existingCartRecord.equals(cartRecord)){
                    BigDecimal cartItemPrice = cartRecord.getCartItemTotalPrice();
                    cartRecord = existingCartRecord;
                    cartRecord.setCartItemQuantity(existingCartRecord.getCartItemQuantity() + cartItemQuantity);
                    cartRecord.setCartItemTotalPrice(existingCartRecord.getCartItemTotalPrice().add(cartItemPrice));
                }
            }
        }
        return cartRecord;
    }

    /**
     * POST  /cart/{cartId} : removes the given cart record
     *
     * @param cartId
     * @return the ResponseEntity with status 200 (OK)
     */
    @PostMapping("/cart/{cartId}")
    @Timed
    public ResponseEntity<Cart> removeFromCart(@PathVariable("cartId") Long cartId) {
        Cart cartRecord = cartService.findOneByCartId(cartId);
        cartService.remove(cartRecord);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * POST  /cart/updateCartQuantity : updateCartQuantity.
     *
     * @return
     */
    @PostMapping(path = "/cart/updateCartQuantity",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity updateCartQuantity(@RequestBody List<Cart> cartInvoices) {
        for (Cart cartRecord : cartInvoices) {
            Optional<Products> product = productsService.getProductsByProductsId(cartRecord.getProductsId());
            if (product.isPresent()){
                cartRecord.setCartItemQuantity(cartRecord.getCartItemQuantity());
                cartRecord.setCartItemTotalPrice(product.get().getProductsPrice().multiply(BigDecimal.valueOf(cartRecord.getCartItemQuantity())));
                cartService.save(cartRecord);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
