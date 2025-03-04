package com.MarketplaceBack.marketplaceBack.models;
import jakarta.persistence.*; // Importa las anotaciones de JPA para el mapeo de la entidad.
import java.time.LocalDate; // Importa la clase para manejar fechas.
import java.util.Collection;
import java.util.List;

import lombok.Builder;
import lombok.Data; // Lombok para generar getters, setters, equals, hashCode y toString.
import lombok.NoArgsConstructor; // Lombok para generar el constructor sin argumentos.
import lombok.AllArgsConstructor; // Lombok para generar el constructor con todos los argumentos.
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Clase que representa la entidad Usuario, mapeada a la tabla "usuarios" en la base de datos.
 */
@Entity // Indica que esta clase es una entidad de JPA.
@Table(name = "usuarios") // Especifica el nombre de la tabla en la base de datos que se asocia con esta entidad.
@NoArgsConstructor // Lombok: Genera un constructor sin argumentos.
@AllArgsConstructor // Lombok: Genera un constructor con todos los campos.
@Builder
public class Usuario {
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public LocalDate getFechaN() {
        return fechaN;
    }

    public void setFechaN(LocalDate fechaN) {
        this.fechaN = fechaN;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define la estrategia de generación del valor (auto_increment en la BD).
    @Column(name = "idUsuario") // Mapea este campo a la columna "idUsuario" de la tabla.
    private Integer idUsuario;

    @Column(name = "nickname", unique = true, nullable = false, length = 100)
    // Mapea a la columna "nickname": valor único, no nulo y con longitud máxima de 100.
    private String nickname;

    @Column(name = "pass", nullable = false, length = 257)
    // Mapea a la columna "pass": no nulo y con longitud máxima de 254.
    private String pass;

    @Column(name = "Nombres", nullable = false, length = 40)
    // Mapea a la columna "Nombres": no nulo y con longitud máxima de 40.
    private String nombres;

    @Column(name = "ApellidoP", nullable = false, length = 20)
    // Mapea a la columna "ApellidoP": no nulo y con longitud máxima de 20.
    private String apellidoP;

    @Column(name = "ApellidoM", nullable = false, length = 20)
    // Mapea a la columna "ApellidoM": no nulo y con longitud máxima de 20.
    private String apellidoM;

    @Column(name = "FechaN", nullable = false)
    // Mapea a la columna "FechaN": no nulo.
    private LocalDate fechaN;

    @Column(name = "Correo", nullable = false, length = 254)
    // Mapea a la columna "Correo": no nulo y con longitud máxima de 254.
    private String correo;

    @Column(name = "telefono", length = 10)
    // Mapea a la columna "Telefono" con una longitud máxima de 10 (puede ser nulo).
    private String telefono;

    @Enumerated(EnumType.STRING)
    // Indica que el enum se almacenará como una cadena (String) en la BD.
    @Column(name = "tipo", length = 10)
    // Mapea a la columna "tipo": con longitud máxima de 10.
    private Tipo tipo;

    @Column(name = "Calle", length = 100)
    // Mapea a la columna "Calle" con una longitud máxima de 100.
    private String calle;

    @Column(name = "Colonia", length = 100)
    // Mapea a la columna "Colonia" con una longitud máxima de 100.
    private String colonia;

    @Column(name = "Lote")
    // Mapea a la columna "Lote".
    private Integer lote;

    @Column(name = "Municipio", length = 30)
    // Mapea a la columna "Municipio" con una longitud máxima de 30.
    private String municipio;

    @Column(name = "verificado")
    // Mapea a la columna "verificado" que en MySQL se suele representar como tinyint(1).
    private Boolean verificado;


    /**
     * Enum que representa el tipo de usuario.
     * Puede ser Cliente o Vendedor.
     */
    public enum Tipo {
        Cliente,
        Vendedor,
        Admin
    }
}
