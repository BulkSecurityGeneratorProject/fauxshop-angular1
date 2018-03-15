package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface FavoriteRepository extends JpaRepository<Favorite, String> {

    Optional<List<Favorite>> findAllById(Long id);

    Favorite findOneByFavoriteId(Long favoriteId);

    void deleteById(Long id);
}
