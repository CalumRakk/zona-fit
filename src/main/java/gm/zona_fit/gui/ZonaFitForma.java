package gm.zona_fit.gui;

import gm.zona_fit.modelo.Usuario;
import gm.zona_fit.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;



    JTextField nombreTexto;
    JTextField apellidoTexto;
    JTextField membresiaTexto;
    JTable tablaUsuarios;
    JButton botonGuardar;
    JButton botonEliminar;
    JButton botonLimpiar;
    private JPanel panelFormulario;
    private JPanel panelTabla;
    private JPanel panelBotones;

    private final UsuarioService usuarioService;

    private Integer idUsuarioFormulario;

    private DefaultTableModel tablaModeloUsuarios;

    @Autowired
    public ZonaFitForma(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        iniciarForma();

        tablaModeloUsuarios = new DefaultTableModel(null, new Object[]{"Id", "Nombre", "Apellido", "Membresía"});
        tablaUsuarios.setModel(tablaModeloUsuarios);
        cargarUsuarios();
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDatosEnFormulario();
            }
        });

        // Listener para el botón Limpiar (ya lo tienes)
        botonLimpiar.addActionListener(e -> {
            limpiarFormulario();
            tablaUsuarios.clearSelection();
        });

        // --- Inicio Código Nuevo para Botón Guardar ---

        // Listener para el botón Guardar
        botonGuardar.addActionListener(e -> {
            // 1. Obtener datos del formulario
            String nombre = nombreTexto.getText();
            String apellido = apellidoTexto.getText();
            String membresia = membresiaTexto.getText();

            // 2. Validar que los campos obligatorios no estén vacíos (validación básica)
            if (nombre.isEmpty() || apellido.isEmpty() || membresia.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Todos los campos (Nombre, Apellido, Membresía) son obligatorios.",
                        "Error de Validación",
                        JOptionPane.WARNING_MESSAGE);
                return; // Detenemos el proceso si la validación falla
            }

            // 3. Crear o actualizar el objeto Usuario
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(this.idUsuarioFormulario); // Si es null, Spring lo tratará como INSERT

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setMembresia(Integer.parseInt(membresia));

            // 4. Llamar al servicio para guardar (insertar o actualizar)
            try {
                usuarioService.guardarUsuario(usuario);
                JOptionPane.showMessageDialog(this,
                        "Usuario guardado correctamente.",
                        "Guardado Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);

                // 5. Refrescar la tabla para mostrar los cambios
                cargarUsuarios();

                // 6. Limpiar el formulario después de guardar
                limpiarFormulario();

            } catch (Exception ex) {
                // Manejo básico de errores (por ejemplo, si falla la conexión con la BD)
                JOptionPane.showMessageDialog(this,
                        "Error al guardar el usuario: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Imprime la traza del error en la consola
            }
        });

        // Listener para el botón Eliminar
        botonEliminar.addActionListener(e -> {
            // 1. Verificar si hay una fila seleccionada
            int filaSeleccionada = tablaUsuarios.getSelectedRow();
            if (filaSeleccionada == -1) {
                // Si no hay fila seleccionada, mostramos un aviso
                JOptionPane.showMessageDialog(this,
                        "Debes seleccionar un usuario en la tabla para eliminar.",
                        "Seleccionar Usuario",
                        JOptionPane.WARNING_MESSAGE);
                return; // Salimos del listener
            }

            // 2. Obtener el ID del usuario de la fila seleccionada
            // Asegúrate que la columna 0 es el ID
            Integer idUsuarioAEliminar = (Integer) tablaModeloUsuarios.getValueAt(filaSeleccionada, 0);

            // 3. Pedir confirmación al usuario
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar este usuario?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION, // Botones Si y No
                    JOptionPane.QUESTION_MESSAGE); // Icono de pregunta

            // 4. Si el usuario confirma (presiona Sí)
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    // Llamar al servicio para eliminar el usuario por su ID
                    usuarioService.eliminarUsuarioPorId(idUsuarioAEliminar);

                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(this,
                            "Usuario eliminado correctamente.",
                            "Eliminación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);

                    // 5. Refrescar la tabla y limpiar el formulario
                    cargarUsuarios();
                    limpiarFormulario(); // Limpiamos también los campos del formulario

                } catch (Exception ex) {
                    // Manejo básico de errores durante la eliminación
                    JOptionPane.showMessageDialog(this,
                            "Error al eliminar el usuario: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Imprime la traza del error
                }
            }
            // Si el usuario presiona No o cierra el diálogo, no hacemos nada
        });

    }
    private void cargarUsuarios() {
        // Limpiamos filas anteriores (en caso de refrescar la tabla)
        tablaModeloUsuarios.setRowCount(0); // Elimina todas las filas existentes

        // Obtener la lista de usuarios desde el servicio
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        // Iterar sobre la lista de usuarios y añadirlos al modelo de la tabla
        usuarios.forEach(usuario -> {
            Object[] fila = {
                    usuario.getIdUsuario(), // Columna 0 (será el ID)
                    usuario.getNombre(),    // Columna 1
                    usuario.getApellido(),  // Columna 2
                    usuario.getMembresia()  // Columna 3
            };
            tablaModeloUsuarios.addRow(fila); // Añade la fila al modelo
        });
    }
    private void iniciarForma() {
        setTitle("Zona Fit (GYM)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setContentPane(panelPrincipal); // ASIGNAMOS EL PANEL PRINCIPAL DEL DISEÑADOR
        pack(); // Ajusta el tamaño de la ventana al contenido
        setLocationRelativeTo(null); // Centra la ventana
    }
    private void mostrarDatosEnFormulario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            Integer idUsuario = (Integer) tablaModeloUsuarios.getValueAt(filaSeleccionada, 0);

            // --- Inicio Código Nuevo ---
            // Guardamos el ID del usuario seleccionado
            this.idUsuarioFormulario = idUsuario;
            // --- Fin Código Nuevo ---

            Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

            if (usuario != null) {
                nombreTexto.setText(usuario.getNombre());
                apellidoTexto.setText(usuario.getApellido());
                membresiaTexto.setText(usuario.getMembresia().toString());
            } else {
                // Si el usuario no se encuentra (eliminado externamente), limpiamos
                limpiarFormulario();
            }
        } else {
            // Si no hay fila seleccionada, limpiar el formulario
            limpiarFormulario();
        }
    }
    private void limpiarFormulario() {
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");
        // --- Inicio Código Nuevo ---
        // Al limpiar el formulario, el ID del usuario actual pasa a ser nulo
        this.idUsuarioFormulario = null;
        // --- Fin Código Nuevo ---
        tablaUsuarios.clearSelection(); // Ya estaba esto
    }

    // Este método es especial y lo usa el diseñador de IntelliJ si necesitas
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}