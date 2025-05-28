package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.DTO.PublicacionDTO;
import com.MarketplaceBack.marketplaceBack.models.Publicaciones;
import com.MarketplaceBack.marketplaceBack.repository.PublicacionesRepository;
import com.MarketplaceBack.marketplaceBack.service.PublicacionesService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicacionController {
    @Autowired
    PublicacionesRepository publicacionesRepository;
    @Autowired
    PublicacionesService publicacionesService;

    @GetMapping({"/un/publicaciones", "/publicaciones"})
    public ResponseEntity<?> getPublicaciones() {

        return ResponseEntity.ok(publicacionesService.getAllPublicaciones());
    }

    @GetMapping("/misPublicaciones")
    public ResponseEntity<?> getMisPublicaciones() {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        return ResponseEntity.ok(publicacionesService.getPublicacionesUsuario(idUsuario));
    }
    @GetMapping({"/Publicaciones/{orden}", "/un/Publicaciones/{orden}"})
    public ResponseEntity<?> getPublicacionesOrdenadas(@PathVariable Integer orden) {
        if (orden == 1) {
            return ResponseEntity.ok(publicacionesService.GetPublicacionesOrdenado());
        }
        else {
            return ResponseEntity.ok(publicacionesService.GetPublicacionesRecientes());
        }
    }

    @GetMapping({"/BuscarPublicaciones/{busqueda}", "/un/BuscarPublicaciones/{busqueda}"})
    public ResponseEntity<?> BuscarPublicaciones(@PathVariable String busqueda){
        return ResponseEntity.ok(publicacionesRepository.PublicacionesPorBusqueda(busqueda));
    }

    @GetMapping({"/un/publicacion/{id}", "/publicacion/{id}"})
    public ResponseEntity<?> getPublicacionById(@PathVariable Integer id) {
        new PublicacionDTO();
        Optional<PublicacionDTO> publicacion;
        publicacion = publicacionesService.getPublicacion(id);
        if (publicacion.isPresent()) {
            return ResponseEntity.ok(publicacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/publicaciones")
    public ResponseEntity<?> addPublicacion(@RequestBody Publicaciones publicacion) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        publicacion.setIdUsuario(idUsuario);
        publicacionesService.savePublicacion(publicacion);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(publicacion);
    }

    @GetMapping("/publicacionesGuardadas")
    public ResponseEntity<?> getPublicacionesGuardadas() {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        return ResponseEntity.ok(publicacionesService.getPublicacionesGuardadas(idUsuario));
    }
}
