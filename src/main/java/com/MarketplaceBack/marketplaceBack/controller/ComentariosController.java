package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import com.MarketplaceBack.marketplaceBack.models.DTO.ComentarioDTO;
import com.MarketplaceBack.marketplaceBack.service.ComentariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<?> getComentariosById(@PathVariable Long id){
        Optional<List<ComentarioDTO>> comentarios;
        comentarios = comentariosService.getComentariosWithNickname(id);
        if (comentarios.isPresent()) {
            return ResponseEntity.ok(comentarios);
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/comentariosP/{id}")
    public ResponseEntity<?> getComentariosByPublicacion(@PathVariable Integer id){

        return ResponseEntity.ok(comentariosService.getComentarioByPublicacion(id));
    }

    @PostMapping("/comentarios")
    public ResponseEntity<?> addComentario(@RequestBody Comentarios comentario) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        comentario.setIdUsuario(idUsuario);
        boolean isSaved = comentariosService.addComentario(comentario);

        if (isSaved) {
            // Si se guardó correctamente, devolvemos 201 Created con un mensaje
            return ResponseEntity.status(HttpStatus.CREATED).body("Comentario guardado exitosamente");
        } else {
            // Si hubo un error, devolvemos 500 Internal Server Error con un mensaje
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo guardar el comentario");
        }
    }
}
