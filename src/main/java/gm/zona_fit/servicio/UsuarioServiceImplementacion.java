package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Usuario;
import gm.zona_fit.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Importamos la anotación @Service

import java.util.List;

@Service // Indica a Spring que esta clase es un componente de servicio y debe ser gestionada
public class UsuarioServiceImplementacion implements UsuarioService {

    // Inyectamos el repositorio de Usuario
    // Spring se encargará de crear una instancia de UsuarioRepository
    // e inyectarla aquí (Dependency Injection)
    private final UsuarioRepository usuarioRepository;

    // Usamos inyección por constructor (preferida)
    @Autowired // Esta anotación indica a Spring que use este constructor para crear el bean
    public UsuarioServiceImplementacion(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override // Anotación opcional pero recomendada para indicar que implementamos un método de la interfaz
    public List<Usuario> listarUsuarios() {
        // Simplemente delegamos la llamada al repositorio
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        // Usamos findById y si no lo encuentra, devolvemos null (o podrías lanzar una excepción)
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        // Delegamos la llamada al repositorio (save sirve tanto para crear como para actualizar)
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuarioPorId(Integer idUsuario) {
        // Delegamos la llamada al repositorio
        usuarioRepository.deleteById(idUsuario);
    }
}