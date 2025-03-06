package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuarios WHERE nickname = :nickname", nativeQuery = true)
    Optional<Usuario> findByNickname(String nickname);
    @Query(value = "SELECT * FROM usuarios WHERE Correo = :email", nativeQuery = true)
    Optional<Usuario> findByEmail(String email);
}
