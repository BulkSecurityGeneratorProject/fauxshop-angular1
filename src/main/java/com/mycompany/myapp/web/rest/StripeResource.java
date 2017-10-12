package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.dto.CardDTO;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
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
     * POST  /charge/{amount}/{cardInfo}
     *
     * @return the ResponseEntity with status 201 (Created) and the chargeMap if it worked, or 500 (Internal Server Error) if it failed
     */
    @PostMapping(path = "/charge/{amount}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity charge(@PathVariable("amount") BigDecimal amount, @RequestBody CardDTO cardInfo) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Stripe.apiKey = "sk_test_pJRywYDR2jqqKi9nn7qQ5ADy";

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", cardInfo.getNumber());
        cardParams.put("exp_month", cardInfo.getExpMonth());
        cardParams.put("exp_year", cardInfo.getExpYear());
        cardParams.put("cvc", cardInfo.getCvc());
        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);

        RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey("sk_test_pJRywYDR2jqqKi9nn7qQ5ADy").build();
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount);
        chargeMap.put("currency", "usd");
        chargeMap.put("source", token.getId()); // obtained via Stripe.js
        try {
            Charge charge = Charge.create(chargeMap, requestOptions);
            log.debug(charge.toString());
            return new ResponseEntity<>(charge, HttpStatus.CREATED);
        } catch (StripeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
