package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
    List<Comentarios> findByIdPublicacion(Integer idPublicacion);
}
