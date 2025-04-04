package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.DTO.PublicacionDTO;
import com.MarketplaceBack.marketplaceBack.models.DTO.PublicacionGDTO;
import com.MarketplaceBack.marketplaceBack.models.Publicaciones;
import com.MarketplaceBack.marketplaceBack.repository.PublicacionesRepository;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionesService {
    @Autowired
    private PublicacionesRepository publicacionesRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<Publicaciones> getAllPublicaciones() {
        return publicacionesRepository.findAll();
    }
    public List<Publicaciones> GetPublicacionesRecientes() {
        return publicacionesRepository.findAllByOrderByFechaDesc();
    }
    public List<Publicaciones> GetPublicacionesOrdenado() {

        return publicacionesRepository.findAllByOrderByNombreAsc();
    }
    public Optional<Publicaciones> getPublicacionById(Integer id) {
        return publicacionesRepository.findById(id);
    }
    public void savePublicacion(Publicaciones publicacion) {
        publicacionesRepository.save(publicacion);
     }
    public Optional<PublicacionDTO> getPublicacion(Integer ID) {
        return publicacionesRepository.findPublicacion(ID);
    }
     public void deletePublicacion(Integer id) {
        publicacionesRepository.deleteById(id);
     }

    public Optional<List<PublicacionGDTO>> getPublicacionesGuardadas(Integer id) {
        return publicacionesRepository.findPublicacionesGuardada(id);
    }
    public Optional<List<Publicaciones>> getPublicacionesUsuario(Integer id) {
        return publicacionesRepository.findPublicacionById(id);
    }
    public Optional<List<Publicaciones>> BuscarPublicaciones(String busqueda) {
        return publicacionesRepository.PublicacionesPorBusqueda(busqueda);
    }
}
