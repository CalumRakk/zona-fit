package gm.zona_fit;

import gm.zona_fit.gui.ZonaFitForma;
import gm.zona_fit.servicio.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext contexto =
				new SpringApplicationBuilder(ZonaFitApplication.class)
						.headless(false)
						.run(args);

		SwingUtilities.invokeLater(() -> {
			ZonaFitForma zonaFitForma = contexto.getBean(ZonaFitForma.class);
			zonaFitForma.setVisible(true);
		});
	}
}