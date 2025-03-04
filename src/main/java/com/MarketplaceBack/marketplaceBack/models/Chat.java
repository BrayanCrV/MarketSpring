package com.MarketplaceBack.marketplaceBack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer  id;

    @Column(name= "fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false )
    private LocalDateTime fecha;

    @Column(name = "idUsuario1")
    private Integer  idUsuario1;

    @Column(name = "idUsuario2")
    private Integer  idUsuario2;

    @Column(name = "mensaje")
    private String mensaje;
}
