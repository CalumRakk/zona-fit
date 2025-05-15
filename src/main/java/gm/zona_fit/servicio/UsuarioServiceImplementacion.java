package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Usuario;
import gm.zona_fit.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplementacion implements UsuarioService {


    private final UsuarioRepository usuarioRepository;


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