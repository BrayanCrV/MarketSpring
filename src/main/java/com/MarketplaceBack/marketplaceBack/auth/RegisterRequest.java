package com.MarketplaceBack.marketplaceBack.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String pass;
    String nombres;
    String apellidoP;
    String apellidoM;
    LocalDate fechaN;
    String correo;
    String telefono;
    String tipo;
    String calle;
    String colonia;
    Integer lote;
    String municipio;
    Boolean verificado;
}
