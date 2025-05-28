package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DireccionDTO {
        private String calle;
        private String colonia;
        private Integer lote;
        private String municipio;


}
