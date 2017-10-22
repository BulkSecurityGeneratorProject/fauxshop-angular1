package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Orders;
import com.mycompany.myapp.domain.OrdersProducts;
import com.mycompany.myapp.repository.OrdersProductsRepository;
import com.mycompany.myapp.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class CheckoutService {

    private final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    private final OrdersRepository ordersRepository;

    private final OrdersProductsRepository ordersProductsRepository;

    public CheckoutService(OrdersRepository ordersRepository, OrdersProductsRepository ordersProductsRepository) {
        this.ordersRepository = ordersRepository;
        this.ordersProductsRepository = ordersProductsRepository;
    }

    public Optional<Orders> getOrdersByOrdersId(Long ordersId) {
        return ordersRepository.findOneByOrderId(ordersId);
    }

    public Orders save(Orders orderRecord) {
        return ordersRepository.save(orderRecord);
    }

    public void remove(Orders orderRecord) {
        ordersRepository.delete(orderRecord);
    }

    public void saveOrdersProducts(OrdersProducts ordersProductsRecordToPersist) {
        ordersProductsRepository.save(ordersProductsRecordToPersist);
    }
}
