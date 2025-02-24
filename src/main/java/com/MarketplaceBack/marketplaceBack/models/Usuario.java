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
@Data // Lombok: Genera automáticamente getters, setters, toString, equals y hashCode.
@NoArgsConstructor // Lombok: Genera un constructor sin argumentos.
@AllArgsConstructor // Lombok: Genera un constructor con todos los campos.
@Builder
public class Usuario implements UserDetails {

    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define la estrategia de generación del valor (auto_increment en la BD).
    @Column(name = "idUsuario") // Mapea este campo a la columna "idUsuario" de la tabla.
    private Integer idUsuario;

    @Column(name = "nickname", unique = true, nullable = false, length = 100)
    // Mapea a la columna "nickname": valor único, no nulo y con longitud máxima de 100.
    private String username;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((tipo.name())));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //se comprueba con el token
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //se comprueba con el token
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //se comprueba con el token
    }

    @Override
    public boolean isEnabled() {
        return true; //se comprueba con el token
    }

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
