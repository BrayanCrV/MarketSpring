package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String nickname);
}
