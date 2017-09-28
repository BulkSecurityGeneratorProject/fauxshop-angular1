package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface ProductsRepository extends JpaRepository<Products, String> {

    Optional<Products> findOneByProductsId(Long productsId);

}
