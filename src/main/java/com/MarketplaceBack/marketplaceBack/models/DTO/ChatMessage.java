package com.MarketplaceBack.marketplaceBack.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ChatMessage {
    private String mensaje;
    private Timestamp fecha;
    private String remitenteNickname;
    private String receptorNickname;
}
