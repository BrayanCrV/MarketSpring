package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import com.MarketplaceBack.marketplaceBack.repository.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComentariosService {
    @Autowired
    private ComentariosRepository comentariosRepository;

//    public ComentariosService(ComentariosRepository comentariosRepository) {
//        this.comentariosRepository = comentariosRepository;
//    }
    public List<Comentarios> getComentarios(){
        return comentariosRepository.findAll();
    }
    public Optional<Comentarios> getComentariosById(int id){
        return comentariosRepository.findById(id);
    }
    public List<Comentarios> getComentarioByPublicacion(int id){
        return comentariosRepository.findByIdPublicacion(id);
    }
}
