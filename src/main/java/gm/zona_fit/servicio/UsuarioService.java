package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Usuario;
import java.util.List; // Importamos la clase List de Java util

public interface UsuarioService {

    // Método para listar todos los usuarios
    public List<Usuario> listarUsuarios();

    // Método para buscar un usuario por su ID
    public Usuario buscarUsuarioPorId(Integer idUsuario);

    // Método para guardar un usuario (nuevo o existente)
    public Usuario guardarUsuario(Usuario usuario);

    // Método para eliminar un usuario por su ID
    public void eliminarUsuarioPorId(Integer idUsuario);
}