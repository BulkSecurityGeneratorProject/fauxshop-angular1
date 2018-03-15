package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Favorite;
import com.mycompany.myapp.repository.FavoriteRepository;
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
public class FavoriteService {

    private final Logger log = LoggerFactory.getLogger(FavoriteService.class);

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public Optional<List<Favorite>> findAllById(Long id) {
        return favoriteRepository.findAllById(id);
    }

    public Favorite findOneByFavoriteId(Long favoriteId) {
        return favoriteRepository.findOneByFavoriteId(favoriteId);
    }

    public Favorite save(Favorite favoriteRecord) {
        return favoriteRepository.save(favoriteRecord);
    }

    public void remove(Favorite favoriteRecord) {
        favoriteRepository.delete(favoriteRecord);
    }

    public void deleteById(Long id) {
        favoriteRepository.deleteById(id);
    }

}
