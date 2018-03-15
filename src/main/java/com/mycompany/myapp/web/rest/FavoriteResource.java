package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Favorite;
import com.mycompany.myapp.domain.Products;
import com.mycompany.myapp.domain.ProductsDescription;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.FavoriteService;
import com.mycompany.myapp.service.ProductsDescriptionService;
import com.mycompany.myapp.service.ProductsService;
import com.mycompany.myapp.service.dto.CartDTO;
import com.mycompany.myapp.service.dto.FavoriteDTO;
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
public class FavoriteResource {

    private final Logger log = LoggerFactory.getLogger(FavoriteResource.class);

    private final FavoriteService favoriteService;
    private final ProductsService productsService;
    private final ProductsDescriptionService productsDescriptionService;

    public FavoriteResource(FavoriteService favoriteService, ProductsService productsService, ProductsDescriptionService productsDescriptionService) {
        this.favoriteService = favoriteService;
        this.productsService = productsService;
        this.productsDescriptionService = productsDescriptionService;
    }

    /**
     * GET  /favorite/{id} : return list of favorite records for the given user id
     *
     * @param id the user id
     * @return the ResponseEntity with status 200 (OK) and the list of Favorite records in the body, or status 500 (Internal Server Error)
     */
    @GetMapping("/favorite/{id}")
    @Timed
    public ResponseEntity<List<FavoriteDTO>> getFavoriteById(@PathVariable("id") Long id) {
        List<FavoriteDTO> favoriteRecords = new ArrayList<>();
        Optional<List<Favorite>> favorite = favoriteService.findAllById(id);

        if (favorite.isPresent()) {
            for (Favorite favoriteItem : favorite.get()) {
                FavoriteDTO favoriteRecord;
                Products product = productsService.getProductsByProductsId(favoriteItem.getProductsId()).orElse(null);
                ProductsDescription productsDescription = productsDescriptionService.getProductsDescriptionByProductsId(favoriteItem.getProductsId()).orElse(null);
                favoriteRecord = new FavoriteDTO(favoriteItem, product, productsDescription);
                favoriteRecords.add(favoriteRecord);
            }
            return new ResponseEntity<List<FavoriteDTO>>(favoriteRecords, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST  /favorite/{id}/{productsId} : saves the given favorite record
     *
     * @param id the user id
     * @return the ResponseEntity with status 200 (OK) and the Favorite record that was saved, or status 500 (Internal Server Error), or status 400 (Bad Request) if the product does not exist
     */
    @PostMapping("/favorite/{id}/{productsId}")
    @Timed
    public ResponseEntity<Favorite> addToFavorite(@PathVariable("id") Long id, @PathVariable("productsId") Long productsId) {
        Favorite favoriteRecord = new Favorite();
        Optional<Products> productOptional = productsService.getProductsByProductsId(productsId);

        if (productOptional.isPresent()){
            favoriteRecord.setId(id);
            favoriteRecord.setProductsId(productsId);

            Favorite favoriteRecordToPersist = examineExistingFavoriteRecords(favoriteRecord);

            try {
                return new ResponseEntity<Favorite>(favoriteService.save(favoriteRecordToPersist), HttpStatus.OK);
            } catch( Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Favorite examineExistingFavoriteRecords(Favorite favoriteRecord) {
        Optional<List<Favorite>> existingCartRecords = favoriteService.findAllById(favoriteRecord.getId());
        if (existingCartRecords.isPresent()) {
            for (Favorite existingCartRecord : existingCartRecords.get()) {
                if (existingCartRecord.equals(favoriteRecord)){
                    favoriteRecord = existingCartRecord;
                }
            }
        }
        return favoriteRecord;
    }

    /**
     * POST  /favorite/{favoriteId} : removes the given favorite record
     *
     * @param favoriteId
     * @return the ResponseEntity with status 200 (OK)
     */
    @PostMapping("/favorite/{favoriteId}")
    @Timed
    public ResponseEntity<Favorite> removeFromCart(@PathVariable("favoriteId") Long favoriteId) {
        Favorite favoriteRecord = favoriteService.findOneByFavoriteId(favoriteId);
        favoriteService.remove(favoriteRecord);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
