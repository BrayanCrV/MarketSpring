package com.MarketplaceBack.marketplaceBack.controller;

import com.MarketplaceBack.marketplaceBack.models.DTO.Login;
import com.MarketplaceBack.marketplaceBack.models.DTO.ResponseDTO;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import com.MarketplaceBack.marketplaceBack.service.IAuthService;
import com.MarketplaceBack.marketplaceBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(("/api/auth"))
public class LoginController {
    @Autowired
    IAuthService authService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> register(@RequestBody Usuario usuario) throws Exception {
        return new ResponseEntity<>(authService.register(usuario), HttpStatus.OK);
    }
    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody Login loginRequest) throws Exception {
        HashMap<String, String> login = authService.login(loginRequest);
        if (login.containsKey("jwt")){
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(login, HttpStatus.BAD_REQUEST);
        }

    }
    
}
