package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Guardados;
import com.MarketplaceBack.marketplaceBack.service.GuardadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuardadosController {
    @Autowired
    GuardadosService guardadosService;

    @GetMapping("/verificarGuardado")
    public ResponseEntity<Boolean> verificarGuardado(
            @RequestParam Integer idUsuario,
            @RequestParam Integer idPublicacion) {
        boolean existe = guardadosService.isPublicacionGuardada(idUsuario, idPublicacion);
        if(existe) {
            return ResponseEntity.ok(existe);
        } else {
            return ResponseEntity.notFound().build();}

    }
    @GetMapping("/guardados/{id}")
    public ResponseEntity<List<Guardados>> getGuardados(@PathVariable Integer id) {
        return  ResponseEntity.ok(guardadosService.obtenerGuardadosPorUsuario(id));
    }
}
