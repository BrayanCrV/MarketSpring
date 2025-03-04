package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import com.MarketplaceBack.marketplaceBack.models.DTO.ComentarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
    List<Comentarios> findByIdPublicacion(Integer idPublicacion);
    @Query(value = "select c.id , u.nickname ,c.fecha, c.comentario from\n" +
            "comentarios as c\n" +
            "left join usuarios as u on u.idUsuario = c.idUsuario\n" +
            "where c.idPublicacion = :idPublicacion", nativeQuery = true )
    Optional<List<ComentarioDTO>> findByPublicacionP(Integer idPublicacion);
}
