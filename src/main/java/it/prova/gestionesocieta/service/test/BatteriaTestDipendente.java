package it.prova.gestionesocieta.service.test;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.service.DipendenteService;
import it.prova.gestionesocieta.service.SocietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BatteriaTestDipendente {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private SocietaService societaService;

    public void testInserimentoDipendenteConControllo() throws Exception {
        System.out.println("INIZIO TEST INSERIMENTO DIPENDENTE CON CONTROLLO");
        //inserisco la società e i dipendenti
        Societa nuovaSocieta = new Societa();
        nuovaSocieta.setRagioneSociale("NextGen Solutions SRL");
        nuovaSocieta.setIndirizzo("Via Milano 25, Bologna");
        nuovaSocieta.setDataFondazione(LocalDate.of(2018, 5, 10));
        societaService.inserisciNuovo(nuovaSocieta);

        Dipendente dipendente1 = new Dipendente();
        dipendente1.setNome("Alessandro");
        dipendente1.setCognome("Romano");
        dipendente1.setDataAssunzione(LocalDate.of(2023, 3, 15));
        dipendente1.setRedditoAnnuoLordo(34000);
        dipendente1.setSocieta(nuovaSocieta);
        dipendenteService.inserisciNuovoConControllo(nuovaSocieta.getId(), dipendente1);

        Dipendente dipendente2 = new Dipendente();
        dipendente2.setNome("Martina");
        dipendente2.setCognome("De Luca");
        dipendente2.setDataAssunzione(LocalDate.of(2016, 9, 1));
        dipendente2.setRedditoAnnuoLordo(36000);
        dipendente2.setSocieta(nuovaSocieta);
        dipendenteService.inserisciNuovoConControllo(nuovaSocieta.getId(), dipendente2);

        System.out.println("FINE TEST INSERIMENTO DIPENDENTE CON CONTROLLO: CON SUCCESSO");
    }
    }
