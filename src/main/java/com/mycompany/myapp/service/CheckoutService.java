package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Orders;
import com.mycompany.myapp.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class CheckoutService {

    private final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    private final OrdersRepository ordersRepository;

    public CheckoutService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders save(Orders orderRecord) {
        return ordersRepository.save(orderRecord);
    }

    public void remove(Orders orderRecord) {
        ordersRepository.delete(orderRecord);
    }
}
