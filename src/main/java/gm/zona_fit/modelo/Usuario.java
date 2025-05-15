package gm.zona_fit.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Indica que esta clase es una entidad JPA (se mapeará a una tabla en la BD)
@Data // Lombok: genera automáticamente getters, setters, equals, hashCode, toString
@NoArgsConstructor // Lombok: genera un constructor sin argumentos
@AllArgsConstructor // Lombok: genera un constructor con todos los argumentos
@ToString // Lombok: genera el método toString (aunque @Data ya lo incluye, es bueno ser explícito)
public class Usuario {

    @Id // Indica que este campo es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el ID es autoincremental, gestionado por la BD
    private Integer idUsuario; // Usamos Integer para permitir nulos si fuera necesario, aunque como PK no lo será

    private String nombre;
    private String apellido;
    private Integer membresia; // Podríamos considerar un tipo numérico o incluso otra entidad si la membresía tiene más lógica
}