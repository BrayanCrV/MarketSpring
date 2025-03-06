package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @AllArgsConstructor
    @Data
    public class PublicacionGDTO {
        private Integer idPublicacion; // Añadido para mapear idPublicacion
        private String foto;
        private String nombre; // Cambiado a minúscula para seguir convenciones
        private float precio;
    }



