package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatList {
    private String nickname2;
    private String mensaje;
    private Timestamp fecha;
    private Integer otroUsuario;
    private String enviadoPor;
}
