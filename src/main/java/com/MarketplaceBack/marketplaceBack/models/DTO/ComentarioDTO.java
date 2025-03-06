package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.sql.Timestamp;


@Data
@AllArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String nickname;
    private Timestamp fecha;
    private String comentario;


}
