package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class DireccionDTO {
        private String calle;
        private String colonia;
        private Integer lote;
        private String municipio;


}
