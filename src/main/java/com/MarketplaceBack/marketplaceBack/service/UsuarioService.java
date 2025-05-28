package com.MarketplaceBack.marketplaceBack.service;

import com.MarketplaceBack.marketplaceBack.models.DTO.DireccionDTO;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuario(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public void saveOrUpdate(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    public void delete(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
    public Optional<Usuario> getUsuarioByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname);
    }
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional
    public void actualizarDireccion(Integer idUsuario, DireccionDTO direccionDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setCalle(direccionDTO.getCalle());
        usuario.setColonia(direccionDTO.getColonia());
        usuario.setLote(direccionDTO.getLote());
        usuario.setMunicipio(direccionDTO.getMunicipio());

        usuarioRepository.save(usuario);
    }
}
