package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class CartService {

    private final Logger log = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<List<Cart>> findAllById(Long id) {
        return cartRepository.findAllById(id);
    }

    public Cart save(Cart cartRecord) {
        return cartRepository.save(cartRecord);
    }
}
