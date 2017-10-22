package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface OrdersRepository extends JpaRepository<Orders, String> {

    Optional<Orders> findOneByOrderId(Long orderId);
}
