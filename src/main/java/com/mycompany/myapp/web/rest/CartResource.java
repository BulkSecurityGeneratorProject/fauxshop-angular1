package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Products;
import com.mycompany.myapp.domain.ProductsDescription;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.ProductsDescriptionService;
import com.mycompany.myapp.service.ProductsService;
import com.mycompany.myapp.service.dto.CartDTO;
import com.mycompany.myapp.service.dto.ProductsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                Products product = productsService.getProductsByProductsId(cartItem.getProductsId()).get();
                ProductsDescription productsDescription = productsDescriptionService.getProductsDescriptionByProductsId(cartItem.getProductsId()).get();
                cartRecord = new CartDTO(cartItem, product, productsDescription);
                cartRecords.add(cartRecord);
            }
            return new ResponseEntity<List<CartDTO>>(cartRecords, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
