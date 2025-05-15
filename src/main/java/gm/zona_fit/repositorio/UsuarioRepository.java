package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Spring Data JPA generará automáticamente las implementaciones para los métodos
    // básicos como save(), findById(), findAll(), delete(), etc.
}