package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrdersProducts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface OrdersProductsRepository extends JpaRepository<OrdersProducts, String> {

}
