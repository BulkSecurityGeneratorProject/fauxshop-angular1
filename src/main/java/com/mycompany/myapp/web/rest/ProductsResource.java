package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.ProductsService;
import com.mycompany.myapp.service.dto.ProductsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public ProductsResource(ProductsService productsService) {
        this.productsService = productsService;
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

        ResponseEntity<ProductsDTO> productsDTO = ResponseUtil.wrapOrNotFound(
            productsService.getProductsByProductsId(productsId)
                .map(ProductsDTO::new));

        log.error(productsDTO.toString());

        return productsDTO;
    }
}
