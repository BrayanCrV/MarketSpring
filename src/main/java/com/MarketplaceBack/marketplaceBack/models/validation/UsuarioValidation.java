package com.MarketplaceBack.marketplaceBack.models.validation;

import com.MarketplaceBack.marketplaceBack.models.DTO.ResponseDTO;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioValidation {
    @Autowired
    UsuarioService usuarioService;
    public ResponseDTO validate(Usuario usuario) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setNumOfErrors(0);

        if (usuarioService.getUsuarioByNickname(usuario.getNickname()).isPresent()) {
            responseDTO.setNumOfErrors(responseDTO.getNumOfErrors() +1);
            responseDTO.setMessage("El usuario que ingresaste se encuentra en uso");

        }
        if (usuarioService.getUsuarioByEmail(usuario.getCorreo()).isPresent()) {
            responseDTO.setNumOfErrors(responseDTO.getNumOfErrors() +1);
            responseDTO.setMessage("El correo que ingresaste se encuentra en uso");

        }
        if (responseDTO.getNumOfErrors() == 2){
            responseDTO.setMessage("El correo y usuario que ingresaste se encuentra en uso");
        }

        return responseDTO;
    }
}
