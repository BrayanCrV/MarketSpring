package com.MarketplaceBack.marketplaceBack.controller;
import com.MarketplaceBack.marketplaceBack.models.DTO.DireccionDTO;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import com.MarketplaceBack.marketplaceBack.service.GuardadosService;
import com.MarketplaceBack.marketplaceBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;



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
    @GetMapping("/usuariosN/{nickname}")
    public ResponseEntity<?> getUsuario(@PathVariable String nickname ) {
        new Usuario();
        Optional<Usuario> User;
        User = usuarioService.getUsuarioByNickname(nickname);
        if ( User.isPresent()) {
            return ResponseEntity.ok(User);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/direccion")
    public ResponseEntity<?> putDireccion(@RequestBody DireccionDTO direccionDTO) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        usuarioService.actualizarDireccion(idUsuario, direccionDTO);
        return ResponseEntity.ok().build();
    }

}
