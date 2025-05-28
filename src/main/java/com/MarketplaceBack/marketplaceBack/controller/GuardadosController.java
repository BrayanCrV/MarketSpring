package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.Guardados;
import com.MarketplaceBack.marketplaceBack.service.GuardadosService;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuardadosController {
    @Autowired
    GuardadosService guardadosService;

    @GetMapping("/guardados/{idPublicacion}")
    public ResponseEntity<?> verificarGuardado (@PathVariable Integer idPublicacion) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
       Integer idUsuario = Integer.valueOf(id);
        boolean existe = guardadosService.isPublicacionGuardada(idUsuario, idPublicacion);
        if(existe) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);}


    }
    @GetMapping("/guardados")
    public ResponseEntity<List<Guardados>> getGuardados() {
        return ResponseEntity.ok().build();
        //return  ResponseEntity.ok(guardadosService.obtenerGuardadosPorUsuario(id));
    }
    @DeleteMapping("/guardados/{idPublicacion}")
    public ResponseEntity<?> eliminarGuardado(@PathVariable Integer idPublicacion) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        guardadosService.EliminarGuardado(idPublicacion, idUsuario);
        return ResponseEntity.ok().body("Guardado eliminado correctamente");
    }

    @PostMapping("guardados/{idPublicacion}")
    public ResponseEntity<?> guardarGuardado(@PathVariable Integer idPublicacion) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer idUsuario = Integer.valueOf(id);
        Guardados  guardado = new Guardados();
        guardado.setIdUsuario(idUsuario);
        guardado.setIdPublicacion(idPublicacion);
        guardado = guardadosService.guardarGuardados(guardado);
        return ResponseEntity.ok().body(guardado);
    }
}
