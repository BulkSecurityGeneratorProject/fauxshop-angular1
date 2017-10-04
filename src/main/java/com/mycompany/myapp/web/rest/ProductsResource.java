package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Products;
import com.mycompany.myapp.domain.ProductsDescription;
import com.mycompany.myapp.service.ProductsDescriptionService;
import com.mycompany.myapp.service.ProductsService;
import com.mycompany.myapp.service.dto.ProductsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class ProductsResource {

    private final Logger log = LoggerFactory.getLogger(ProductsResource.class);

    private final ProductsService productsService;
    private final ProductsDescriptionService productsDescriptionService;

    public ProductsResource(ProductsService productsService, ProductsDescriptionService productsDescriptionService) {

        this.productsService = productsService;
        this.productsDescriptionService = productsDescriptionService;
    }

    /**
     * GET  /products/{productsId} : activate the registered user.
     *
     * @param productsId the productsId (primary key)
     * @return the ResponseEntity with status 200 (OK) and the activated user in body, or status 500 (Internal Server Error)
     */
    @GetMapping("/products/{productsId}")
    @Timed
    public ResponseEntity<ProductsDTO> getProduct(@PathVariable("productsId") Long productsId) {
        Optional<Products> products = productsService.getProductsByProductsId(productsId);
        Optional<ProductsDescription> productsDescription = productsDescriptionService.getProductsDescriptionByProductsId(productsId);

        if (products.isPresent() && productsDescription.isPresent()) {
            return new ResponseEntity<>(new ProductsDTO(products.get(), productsDescription.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
