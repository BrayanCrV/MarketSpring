package com.MarketplaceBack.marketplaceBack.service;

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
    public Optional<Publicaciones> getPublicacionById(int id) {
        return publicacionesRepository.findById(id);
    }
     public void saveOrUpdatePublicacion(Publicaciones publicacion) {
        publicacionesRepository.save(publicacion);
     }

     public void deletePublicacion(int id) {
        publicacionesRepository.deleteById(id);
     }


}
