package it.prova.gestionesocieta;

import it.prova.gestionesocieta.service.test.BatteriaTestDipendente;
import it.prova.gestionesocieta.service.test.BatteriaTestProgetto;
import it.prova.gestionesocieta.service.test.BatteriaTestSocieta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionesocietaApplication implements CommandLineRunner {
	@Autowired
	private BatteriaTestDipendente testDipendente;
	@Autowired
	private BatteriaTestSocieta testSocieta;
	@Autowired
	private BatteriaTestProgetto testProgetto;

	public static void main(String[] args) {
		SpringApplication.run(GestionesocietaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//società
		//testSocieta.testInserimentoERimozioneDiSocieta();
		//testSocieta.testFindByQuery();

		//dipendente
		//testDipendente.testInserimentoDipendenteConControllo();
		//testDipendente.testAbbinamentoDipendenteAProgetti();

		//progetto
		testProgetto.testAbbinamentoProgettoAPiuDipendenti();
	}
}
