package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Publicaciones;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.repository.PublicacionesRepository;
import com.MarketplaceBack.marketplaceBack.service.PublicacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicacionController {
    @Autowired
    PublicacionesRepository publicacionesRepository;
    @Autowired
    PublicacionesService publicacionesService;

    @GetMapping("/publicaciones")
    public ResponseEntity<?> getPublicaciones() {
        return ResponseEntity.ok(publicacionesService.getAllPublicaciones());
    }
    @GetMapping("/publicaciones/{id}")
    public ResponseEntity<?> getPublicacionById(@PathVariable Integer id) {
        new Publicaciones();
        Optional<Publicaciones> publicacion;
        publicacion = publicacionesService.getPublicacionById(id);
        if (publicacion.isPresent()) {
            return ResponseEntity.ok(publicacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/publicaciones")
    public ResponseEntity<?> addPublicacion(@RequestBody Publicaciones publicacion) {
        publicacionesService.saveOrUpdatePublicacion(publicacion);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(publicacion);
    }}
