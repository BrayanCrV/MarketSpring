package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.Guardados;
import com.MarketplaceBack.marketplaceBack.repository.GuardadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class GuardadosService {
    @Autowired
    private GuardadosRepository guardadosRepository;

    // Verifica si un usuario tiene guardada una publicación
    public boolean isPublicacionGuardada(Integer idUsuario, Integer idPublicacion) {
        return guardadosRepository.existsByIdUsuarioAndIdPublicacion(idUsuario, idPublicacion);
    }

    // Obtiene todos los guardados de un usuario
    public List<Guardados> obtenerGuardadosPorUsuario(Integer idUsuario) {
        return guardadosRepository.findByIdUsuario(idUsuario);
    }
    @Transactional
    public void EliminarGuardado(Integer idPublicacion, Integer idUsuario) {
        guardadosRepository.deleteByIdPublicacionAndIdUsuario(idPublicacion, idUsuario);
    }


    public Guardados guardarGuardados(Guardados guardados) {
        guardadosRepository.save(guardados);
        return guardados;
    }
}
