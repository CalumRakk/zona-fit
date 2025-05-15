package gm.zona_fit.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Indica que esta clase es una entidad JPA (se mapeará a una tabla en la BD)
@Data // Lombok: genera automáticamente getters, setters, equals, hashCode, toString
@NoArgsConstructor // Lombok: genera un constructor sin argumentos
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombre;
    private String apellido;
    private Integer membresia;
}