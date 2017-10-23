package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface CartRepository extends JpaRepository<Cart, String> {

    Optional<List<Cart>> findAllById(Long id);

    Cart findOneByCartId(Long cartId);

    void deleteById(Long id);
}
