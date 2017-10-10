package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class StripeResource {

    private final Logger log = LoggerFactory.getLogger(StripeResource.class);

    public StripeResource() {
    }

    /**
     * POST  /charge/{amount}/{token}
     *
     * @return the ResponseEntity with status 201 (Created) if the user is registered or 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping(path = "/charge/{amount}/{token}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity checkout(@PathVariable("cartId") BigDecimal amount, @PathVariable("token") String token) {
        RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey("sk_test_pJRywYDR2jqqKi9nn7qQ5ADy").build();
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount);
        chargeMap.put("currency", "usd");
        chargeMap.put("source", token); // obtained via Stripe.js
        try {
            Charge charge = Charge.create(chargeMap, requestOptions);
            log.debug(charge.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (StripeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
