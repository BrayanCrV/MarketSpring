package com.MarketplaceBack.marketplaceBack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "publicaciones")
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Publicaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublicacion")
    private Integer idPublicacion;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(name = "Nombre", nullable = false, length = 100 )
    private String nombre;

    @Column(name = "Precio", nullable = false)
    private float precio;

    @Column(name = "Cantidad", nullable = false, length = 30)
    private String cantidad;

    @Column(name= "Descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "foto", length = 460)
    private String foto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tunidad", nullable= false)
    private Unidad tunidad;


    public enum Unidad {
        kilo, pieza
    }
}
