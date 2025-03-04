package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.Data;

@Data
    public class PublicacionDTO {
        private Integer id; // Añadido para mapear idPublicacion
        private String foto;
        private String precio;
        private String nombre; // Cambiado a minúscula para seguir convenciones
        private String descripcion;
        private String tunidad; // Cambiado para coincidir con la columna tunidad
        private String cantidad; // Cambiado para coincidir con la columna Cantidad
        private String nombreC; // Cambiado para coincidir con u.Nombres as Nombre
        private String nickname;
    }

