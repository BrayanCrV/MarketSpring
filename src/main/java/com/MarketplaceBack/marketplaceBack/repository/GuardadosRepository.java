package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Guardados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuardadosRepository extends JpaRepository<Guardados, Integer> {
    List<Guardados> findByIdUsuario(Integer idUsuario);
    boolean existsByIdUsuarioAndIdPublicacion(Integer idUsuario, Integer idPublicacion);
    void deleteByIdPublicacionAndIdUsuario(Integer idPublicacion, Integer idUsuario);



}
