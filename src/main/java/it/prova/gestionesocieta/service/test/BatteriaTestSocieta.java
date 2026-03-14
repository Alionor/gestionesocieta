package it.prova.gestionesocieta.service.test;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.service.DipendenteService;
import it.prova.gestionesocieta.service.SocietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatteriaTestSocieta {

    @Autowired
    private SocietaService societaService;

    @Autowired
    private DipendenteService dipendenteService;

    public void testInserimentoERimozioneDiSocieta() throws  Exception {
        System.out.println("INIZIO TEST INSERIMENTO E RIMOZIONE SOCIETA");
            //inserisco la società e i dipendenti
        Societa nuovaSocieta = new Societa();
        nuovaSocieta.setRagioneSociale("NextGen Solutions SRL");
        nuovaSocieta.setIndirizzo("Via Milano 25, Bologna");
        nuovaSocieta.setDataFondazione(LocalDate.of(2018,5,10));
        societaService.inserisciNuovo(nuovaSocieta);

        Dipendente dipendente1 = new Dipendente();
        dipendente1.setNome("Alessandro");
        dipendente1.setCognome("Romano");
        dipendente1.setDataAssunzione(LocalDate.of(2023,3,15));
        dipendente1.setRedditoAnnuoLordo(34000);
        dipendente1.setSocieta(nuovaSocieta);
        dipendenteService.inserisciNuovo(dipendente1);

        Dipendente dipendente2 = new Dipendente();
        dipendente2.setNome("Martina");
        dipendente2.setCognome("De Luca");
        dipendente2.setDataAssunzione(LocalDate.of(2022,9,1));
        dipendente2.setRedditoAnnuoLordo(36000);
        dipendente2.setSocieta(nuovaSocieta);
        dipendenteService.inserisciNuovo(dipendente2);

        // reinserisco la societa con controllo (va in errore: commentare questa parte per far continuare la batteria)
        // societaService.inserisciNuovoConControllo(nuovaSocieta);

        // cerco di rimuovere la società (va in errore: commentare questa parte per far continuare la batteria)
        // societaService.rimuoviConControllo(nuovaSocieta.getId());

     /*   // rimuovo scollegando i dipendenti
        societaService.scollegaDipendentiDaSocieta(nuovaSocieta.getId());
        societaService.rimuovi(nuovaSocieta.getId()); */

        System.out.println("FINE TEST INSERIMENTO E RIMOZIONE SOCIETA: CON SUCCESSO");
    }

    public void testFindByQuery() {
        System.out.println("INIZIO testFindByQuery");
            //inserisco delle società
        List<Societa> listaSocieta = List.of(
                new Societa("TechVision SRL", "Via Roma 12, Milano", LocalDate.of(2015,3,18), null),
                new Societa("GreenFuture SPA", "Via Torino 45, Torino", LocalDate.of(2012,9,7), LocalDate.of(2020,1,22)),
                new Societa("DigitalWave SRL", "Via Firenze 8, Firenze", LocalDate.of(2020,1,22), null),
                new Societa("Innovatek SRL", "Via Napoli 63, Firenze", LocalDate.of(2016,11,3), LocalDate.of(2020,1,22))
        );

        for (Societa s : listaSocieta) {
            societaService.inserisciNuovo(s);
        }

        Societa societaExample = new Societa();
        societaExample.setIndirizzo("Firenze");
        System.out.println(societaService.findByExample(societaExample));
        societaExample.setRagioneSociale("Digital");
        System.out.println(societaService.findByExample(societaExample));
        societaExample = new Societa();
        societaExample.setDataFondazione(LocalDate.of(2012,9,07));
        System.out.println(societaService.findByExample(societaExample));

        System.out.println("FINE testFindByQuery: CON SUCCESSO");
    }

}
