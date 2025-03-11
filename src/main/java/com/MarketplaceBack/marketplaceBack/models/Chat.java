package com.MarketplaceBack.marketplaceBack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat
{@Id
@GeneratedValue( strategy = GenerationType.IDENTITY)
@Column(name ="id")
private Integer  id;

    @Column(name= "fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false )
    private Timestamp fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario1" , insertable = false, updatable = false)
    @JsonIgnore // Evita recursión infinita en JSON
    private Usuario usuario1;

    // Relación con el Usuario 2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario2" , insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario2;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "idUsuario1")
    private Integer  idUsuario1;

    @Column(name = "idUsuario2")
    private Integer  idUsuario2;
}
