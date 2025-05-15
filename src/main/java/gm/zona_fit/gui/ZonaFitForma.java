package gm.zona_fit.gui;

import gm.zona_fit.servicio.UsuarioService; // Lo necesitaremos más tarde
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; // Para que Spring lo gestione

import javax.swing.*;
import java.awt.*; // Para BorderLayout, etc.

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

    @Autowired // Inyección por constructor
    public ZonaFitForma(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        iniciarForma();
        // Aquí aún no cargamos datos, solo configuramos la UI
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