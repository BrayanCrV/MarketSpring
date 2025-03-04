package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import com.MarketplaceBack.marketplaceBack.models.DTO.ComentarioDTO;
import com.MarketplaceBack.marketplaceBack.service.ComentariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ComentariosController {
    @Autowired
    ComentariosService comentariosService;

    @GetMapping("/comentarios")
    public ResponseEntity<?> getComentarios(){

        System.out.println("TODOS");
        return ResponseEntity.ok(comentariosService.getComentarios());
    }

    @GetMapping("/comentarios/{idPublicacion}")
    public ResponseEntity<?> getComentariosById(@PathVariable Integer idPublicacion){
        new Comentarios();
        Optional<List<ComentarioDTO>> comentarios;
        comentarios = comentariosService.getComentariosWithNickname(idPublicacion);
        System.out.println("Hola");
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
