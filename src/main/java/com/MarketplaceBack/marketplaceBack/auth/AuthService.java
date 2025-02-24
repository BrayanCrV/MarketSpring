package com.MarketplaceBack.marketplaceBack.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MarketplaceBack.marketplaceBack.jwt.JwtService;
import com.MarketplaceBack.marketplaceBack.models.Usuario;
import com.MarketplaceBack.marketplaceBack.repository.UsuarioRepository;
import com.MarketplaceBack.marketplaceBack.service.UsuarioService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthService {
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public AuthResponse login(LoginRequest request) {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPass()));
            UserDetails user=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
            String token=jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();


    }


    public AuthResponse register(RegisterRequest request) {
        if (request.getTipo().equals("Cliente")) {
            Usuario usuario = Usuario.builder()
                    .username(request.getUsername())
                    .pass(request.getPass())
                    .nombres(request.getNombres())
                    .apellidoP(request.getApellidoP())
                    .apellidoM(request.getApellidoM())
                    .fechaN(request.getFechaN())
                    .correo(request.getCorreo())
                    .telefono(request.getTelefono())
                    .tipo(Usuario.Tipo.Cliente).build();
            usuarioRepository.save(usuario);
        }
        else{
            Usuario usuario = Usuario.builder()
                    .username(request.getUsername())
                    .pass(request.getPass())
                    .nombres(request.getNombres())
                    .apellidoP(request.getApellidoP())
                    .apellidoM(request.getApellidoM())
                    .fechaN(request.getFechaN())
                    .correo(request.getCorreo())
                    .telefono(request.getTelefono())
                    .tipo(Usuario.Tipo.Vendedor)
                    .calle(request.getCalle())
                    .colonia(request.getColonia())
                    .lote(request.getLote())
                    .municipio(request.getMunicipio())
                    .verificado(request.getVerificado())
                    .build();
            usuarioRepository.save(usuario);
            return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
        }
    return null;
    }
}
