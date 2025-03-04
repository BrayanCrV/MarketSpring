package com.MarketplaceBack.marketplaceBack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "guardados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guardados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGuardado")
    private Integer idGuardado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPublicacion", insertable = false, updatable = false)
    @JsonIgnore
    private Publicaciones publicacion;

    @Column(name = "idPublicacion")
    private Integer idPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;

}
