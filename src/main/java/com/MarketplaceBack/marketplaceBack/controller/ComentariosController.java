package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import com.MarketplaceBack.marketplaceBack.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ComentariosController {
    @Autowired
    ComentariosService comentariosService;

    @GetMapping("/comentarios")
    public ResponseEntity<?> getComentarios(){
        return ResponseEntity.ok(comentariosService.getComentarios());
    }
    @GetMapping("/comentarios/{id}")
    public ResponseEntity<?> getComentariosById(@PathVariable Integer id){
        new Comentarios();
        Optional<Comentarios> comentarios;
        comentarios = comentariosService.getComentariosById(id);
        if (comentarios.isPresent()) {
            return ResponseEntity.ok(comentarios);
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/comentariosP/{id}")
    public ResponseEntity<?> getComentariosByPublicacion(@PathVariable Integer id){
        return ResponseEntity.ok(comentariosService.getComentarioByPublicacion(id));
    }
}
