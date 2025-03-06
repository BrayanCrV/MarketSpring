package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.Comentarios;
import com.MarketplaceBack.marketplaceBack.models.DTO.ComentarioDTO;
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
    public Optional<Comentarios> getComentariosById(Integer id){
        return comentariosRepository.findById(id);
    }
    public List<Comentarios> getComentarioByPublicacion(Integer id){
        return comentariosRepository.findByIdPublicacion(id);
    }
    public Optional<List<ComentarioDTO>> getComentariosWithNickname(Long idPublicacion){
        return comentariosRepository.findByPublicacionIDPublicacion(idPublicacion);
    }
    public boolean addComentario(Comentarios comentario){
        try{
            Comentarios savedComentario = comentariosRepository.save(comentario);
            return savedComentario != null;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
