package com.MarketplaceBack.marketplaceBack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "comentarios")
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "idPublicacion", insertable = false, updatable= false)
    @JsonIgnore
    private Publicaciones publicacion;

    @Column(name= "idPublicacion")
    private Integer idPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "idUsuario", insertable = false, updatable= false)
    @JsonIgnore
    private Usuario usuario;

    @Column (name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime fecha;


    @Column (name = "comentario")
    private String comentario;

}
