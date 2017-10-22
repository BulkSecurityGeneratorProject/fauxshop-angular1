package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Orders;
import com.mycompany.myapp.domain.OrdersProducts;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.CheckoutService;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.dto.CardDTO;
import com.mycompany.myapp.service.dto.OrderDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.web.rest.vm.KeyAndPasswordVM;
import com.mycompany.myapp.web.rest.vm.ManagedUserVM;
import com.mycompany.myapp.web.rest.util.HeaderUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class CheckoutResource {

    private final Logger log = LoggerFactory.getLogger(CheckoutResource.class);

    private final CheckoutService checkoutService;

    public CheckoutResource(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * POST  /createOrdersRecord : createOrdersRecord.
     *
     * @return
     */
    @PostMapping(path = "/createOrdersRecord",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity createOrdersRecord(@RequestBody List<Cart> cartInvoices) {
        Orders orderRecordToPersist =  new Orders();
        orderRecordToPersist.setOrderStatus("initiated");
        Orders savedOrderRecord = checkoutService.save(orderRecordToPersist);

        for (Cart cartInvoice : cartInvoices) {
            OrdersProducts ordersProductsRecordToPersist = new OrdersProducts(cartInvoice);
            ordersProductsRecordToPersist.setOrderId(savedOrderRecord.getOrderId());
            checkoutService.saveOrdersProducts(ordersProductsRecordToPersist);
        }

        return new ResponseEntity<>(savedOrderRecord, HttpStatus.CREATED);
    }

    /**
     * POST  /checkout : checkout.
     *
     * @return the ResponseEntity with status 201 (Created) if the checkout processed successfully, or 500 (Internal Server Error) if there was no order record found at the time of checkout.
     */
    @PostMapping(path = "/checkout",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity checkout(@RequestBody OrderDTO orderDTO) {
        Orders orderRecordToPersist;
        Optional<Orders> optionalOrderRecord =  checkoutService.getOrdersByOrdersId(orderDTO.getOrderId());

        if (optionalOrderRecord.isPresent()){
            orderRecordToPersist = new Orders(orderDTO);
            orderRecordToPersist.setOrderId(optionalOrderRecord.get().getOrderId());
            orderRecordToPersist.setOrderStatus("payment_pending");
            Orders savedOrderRecord = checkoutService.save(orderRecordToPersist);

            return new ResponseEntity<>(savedOrderRecord, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * POST  /updateChargeId : updateChargeId.
     *
     * @return the ResponseEntity with status 201 (Created) if the checkout processed successfully, or 500 (Internal Server Error) if there was no order record found at the time of checkout.
     */
    @PostMapping(path = "/updateChargeId",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity updateChargeId(@RequestBody OrderDTO orderDTO) {
        Orders orderRecordToPersist;
        Optional<Orders> optionalOrderRecord =  checkoutService.getOrdersByOrdersId(orderDTO.getOrderId());

        if (optionalOrderRecord.isPresent()){
            orderRecordToPersist = new Orders(orderDTO);
            orderRecordToPersist.setOrderId(optionalOrderRecord.get().getOrderId());
            orderRecordToPersist.setOrderStatus("paid");
            Orders savedOrderRecord = checkoutService.save(orderRecordToPersist);

            return new ResponseEntity<>(savedOrderRecord, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * GET  /checkout : checkout.
     *
     * @return the ResponseEntity with status 201 (Created) if the user is registered or 400 (Bad Request) if the login or email is already in use
     */
    @GetMapping(path = "/checkout",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Timed
    public ResponseEntity checkoutGet() {

        log.error("made it to the end of the checkoutGet method");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
