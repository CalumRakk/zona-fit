package gm.zona_fit; // Asegúrate que este sea tu paquete base

import gm.zona_fit.gui.ZonaFitForma; // Importa tu clase del formulario
import gm.zona_fit.servicio.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*; // Para JFrame

@SpringBootApplication
public class ZonaFitApplication {

	public static void main(String[] args) {
		// Necesitamos configurar Spring para que no sea headless y pueda manejar GUI
		ConfigurableApplicationContext contexto =
				new SpringApplicationBuilder(ZonaFitApplication.class)
						.headless(false) // IMPORTANTE para Swing
						.run(args);

		// Ejecutamos el código para mostrar la GUI en el Event Dispatch Thread (EDT)
		SwingUtilities.invokeLater(() -> {
			// Obtenemos el bean de nuestro formulario desde el contexto de Spring
			ZonaFitForma zonaFitForma = contexto.getBean(ZonaFitForma.class);
			zonaFitForma.setVisible(true);
		});
	}
}