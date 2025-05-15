package gm.zona_fit.gui;

import gm.zona_fit.modelo.Usuario;
import gm.zona_fit.servicio.UsuarioService; // Lo necesitaremos más tarde
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; // Para que Spring lo gestione

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*; // Para BorderLayout, etc.
import java.util.List;

@Component // IMPORTANTE: Para que Spring pueda crear un bean de este formulario
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal; // Asegúrate que este nombre coincida con el del diseñador
    // ... otros JComponents que definiste en el diseñador (nombreTexto, tablaUsuarios, etc.)
    // IntelliJ los generará automáticamente si les diste "Field name"

    // Estos son ejemplos, asegúrate que los nombres de campo sean los que pusiste en el diseñador
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

    private final UsuarioService usuarioService; // Inyectaremos el servicio
    // Objeto para manejar el modelo de la tabla
    private DefaultTableModel tablaModeloUsuarios;

    @Autowired
    public ZonaFitForma(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        iniciarForma(); // Esto inicializa los componentes visuales del .form

        // 1. Configurar el modelo de la tabla
        tablaModeloUsuarios = new DefaultTableModel(null, new Object[]{"Id", "Nombre", "Apellido", "Membresía"});
        tablaUsuarios.setModel(tablaModeloUsuarios);

        // 2. Cargar los datos de los usuarios en la tabla al iniciar
        cargarUsuarios();

        // 3. Configurar el Listener para la selección de filas (ya lo tienes)
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDatosEnFormulario();
            }
        });

        // 4. Configurar el Listener para el botón Limpiar
        botonLimpiar.addActionListener(e -> {
            // Llamamos al método limpiarFormulario
            limpiarFormulario();
            // Además, es buena práctica deseleccionar la fila en la tabla
            tablaUsuarios.clearSelection();
        });

        // --- Fin Código Nuevo para Botón Limpiar ---

        // Aquí agregaremos listeners para Guardar y Eliminar más adelante...
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
        // Aquí es donde IntelliJ añade createUIComponents() si es necesario
        // y donde se inicializan los componentes del .form
    }
    private void mostrarDatosEnFormulario() {
        // Obtenemos el índice de la fila seleccionada
        int filaSeleccionada = tablaUsuarios.getSelectedRow();

        // Verificamos si alguna fila está seleccionada
        if (filaSeleccionada != -1) { // -1 indica que ninguna fila está seleccionada
            // Obtenemos el ID del usuario de la primera columna de la fila seleccionada
            // Asegúrate de que la primera columna (índice 0) en tu modelo sea el ID
            Integer idUsuario = (Integer) tablaModeloUsuarios.getValueAt(filaSeleccionada, 0);

            // Buscamos el usuario completo usando el servicio
            Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

            // Verificamos si el usuario fue encontrado
            if (usuario != null) {
                // Llenamos los campos de texto con los datos del usuario encontrado
                nombreTexto.setText(usuario.getNombre());
                apellidoTexto.setText(usuario.getApellido());
                membresiaTexto.setText(usuario.getMembresia().toString()); // O String.valueOf(usuario.getMembresia()) si fuera numérico
            } else {
                // Esto no debería pasar si el ID de la tabla es válido, pero es buena práctica
                // Limpiar formulario si el usuario no se encuentra (podría haber sido eliminado por otro usuario)
                limpiarFormulario(); // Implementaremos este método después
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
        // Podrías añadir lógica para deshabilitar botones aquí si es necesario
        // También podrías necesitar borrar la selección de la tabla, aunque mostrarDatosEnFormulario
        // ya se encarga de limpiar si no hay selección.
    }

    // Este método es especial y lo usa el diseñador de IntelliJ si necesitas
    // crear componentes con constructores personalizados ANTES de que el diseñador los use.
    // Por ahora, puede que no lo necesites. Si IntelliJ lo añade, déjalo.
    private void createUIComponents() {
        // TODO: place custom component creation code here
        // Por ejemplo, si necesitaras configurar el modelo de la tabla aquí,
        // pero lo haremos más adelante.
    }

    // Método principal para probar el formulario de forma aislada (opcional, ya lo hacemos desde ZonaFitApplication)
        /*
        public static void main(String[] args) {
            // Para probar el diseño sin Spring Boot (no recomendado para la app final)
            SwingUtilities.invokeLater(() -> {
                // No podemos instanciar directamente si depende de Spring (@Autowired)
                // JFrame frame = new ZonaFitForma(null); // Pasar null si no tenemos el servicio
                // frame.setVisible(true);
            });
        }
        */
}