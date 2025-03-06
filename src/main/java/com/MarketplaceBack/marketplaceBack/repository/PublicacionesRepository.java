package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.DTO.PublicacionDTO;
import com.MarketplaceBack.marketplaceBack.models.DTO.PublicacionGDTO;
import com.MarketplaceBack.marketplaceBack.models.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones, Integer>
{
    @Query(value = "SELECT p.idPublicacion as id, p.foto, p.Precio, p.Nombre, p.Descripcion, p.tunidad, p.Cantidad, u.Nombres as NombreC, u.nickname \n" +
            "FROM publicaciones AS p \n" +
            "LEFT JOIN  usuarios AS u ON p.idUsuario = u.idUsuario where idPublicacion = :idPublicacion", nativeQuery = true)
    Optional<PublicacionDTO> findPublicacion( Integer idPublicacion);

    @Query(value= "select p.idPublicacion, p.foto, p.Nombre as nombre, p.Precio from guardados as g\n" +
            "JOIN publicaciones p ON g.idPublicacion = p.idPublicacion\n" +
            "    WHERE g.idUsuario = :idUsuario", nativeQuery = true)
    Optional<List<PublicacionGDTO>> findPublicacionesGuardada(Integer idUsuario);
    @Query(value = "SELECT * FROM publicaciones where idUsuario = :idUsuario", nativeQuery = true)
    Optional<List<Publicaciones>> findPublicacionById(Integer idUsuario);
}

