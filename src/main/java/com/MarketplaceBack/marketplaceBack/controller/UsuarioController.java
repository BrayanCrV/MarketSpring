package com.MarketplaceBack.marketplaceBack.controller;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import com.MarketplaceBack.marketplaceBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<?> getUsuario(@PathVariable Integer idUsuario ) {
        new Usuario();
        Optional<Usuario> User;
        User= usuarioService.getUsuario(idUsuario);
        if ( User.isPresent()) {
            return ResponseEntity.ok(User);
        }
        return ResponseEntity.noContent().build();
    }
}
