package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Usuario; // Importamos nuestra entidad Usuario
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz extiende de JpaRepository, lo que proporciona métodos CRUD básicos
// <Tipo de Entidad, Tipo de la Llave Primaria>
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Spring Data JPA generará automáticamente las implementaciones para los métodos
    // básicos como save(), findById(), findAll(), delete(), etc.
    // Aquí podrías definir métodos personalizados si los necesitaras (por ejemplo, findByNombre(String nombre);)
}