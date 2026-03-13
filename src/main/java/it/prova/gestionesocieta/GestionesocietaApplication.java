package it.prova.gestionesocieta;

import it.prova.gestionesocieta.service.test.BatteriaTestDipendente;
import it.prova.gestionesocieta.service.test.BatteriaTestProgetto;
import it.prova.gestionesocieta.service.test.BatteriaTestSocieta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionesocietaApplication implements CommandLineRunner {

	private BatteriaTestDipendente testDipendente;
	private BatteriaTestSocieta testSocieta;
	private BatteriaTestProgetto testProgetto;

	public static void main(String[] args) {
		SpringApplication.run(GestionesocietaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
