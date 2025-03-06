package com.MarketplaceBack.marketplaceBack.service.impl;

import com.MarketplaceBack.marketplaceBack.models.DTO.Login;
import com.MarketplaceBack.marketplaceBack.models.DTO.ResponseDTO;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.models.validation.UsuarioValidation;
import com.MarketplaceBack.marketplaceBack.service.IAuthService;
import com.MarketplaceBack.marketplaceBack.service.IJWTUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class AuthServiceImpl implements IAuthService {
    private final com.MarketplaceBack.marketplaceBack.service.UsuarioService usuarioService;
    private final IJWTUtilityService jwtUtility;
    private final UsuarioValidation usuarioValidation;

    // Inyecta las dependencias a través del constructor
    @Autowired
    public AuthServiceImpl(com.MarketplaceBack.marketplaceBack.service.UsuarioService usuarioService,
                           IJWTUtilityService jwtUtility,
                           UsuarioValidation usuarioValidation) {
        this.usuarioService = usuarioService;
        this.jwtUtility = jwtUtility;
        this.usuarioValidation = usuarioValidation;
    }

    public HashMap<String,String> login(Login login) throws Exception {
        try{
            HashMap<String,String>  jwt = new HashMap<>();
            Optional<Usuario> usuario = usuarioService.getUsuarioByNickname(login.getNickname());
            if (usuario.isEmpty()){
                jwt.put("error", "Usuario no encontrado");
                return jwt;
            }
            System.out.println(usuario.get().getPass());
            if(verifypassword(login.getPass(), usuario.get().getPass())){
                jwt.put("jwt", jwtUtility.generateJWT(usuario.get().getIdUsuario(), usuario.get().getTipo().toString()));
            }
            else{
                jwt.put("error", "Autentificacion Fallida");
            }
            return jwt;
        }
        catch(Exception e){
            throw new Exception(e.toString());
        }

    }

    public ResponseDTO register(Usuario usuario) throws Exception {

        try {

            ResponseDTO response = usuarioValidation.validate(usuario);
            System.out.println("LLegue aqui");
            if (response.getNumOfErrors() == 0) {
                usuario.setVerificado(false);
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                usuario.setPass(bCryptPasswordEncoder.encode(usuario.getPass()));
                usuarioService.saveOrUpdate(usuario);
                response.setMessage("Usuario registrado");
                System.out.println("Usuario registrado");
                return response;
            }else {

                return response;
            }

        } catch (Exception e) {
            System.out.println("hubo pedillos");
            throw new Exception(e.toString());
        }

    }

    private boolean verifypassword(String enteredPassword, String confirmPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(enteredPassword);
        return passwordEncoder.matches(enteredPassword, confirmPassword );
    }
}
