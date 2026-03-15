package it.prova.gestionesocieta.service.test;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.service.DipendenteService;
import it.prova.gestionesocieta.service.ProgettoService;
import it.prova.gestionesocieta.service.SocietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatteriaTestDipendente {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private SocietaService societaService;
    @Autowired
    private ProgettoService progettoService;

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

    public void testAbbinamentoDipendenteAProgetti() throws Exception {
        System.out.println("INIZIO TEST testAbbinamentoDipendenteAProgetti");
        //inserisco la società, il dipendente e i progetti

        Societa nuovaSocieta = new Societa("NextGen Solutions SRL", "Via Milano 25, Bologna", LocalDate.of(2018, 5, 10), LocalDate.of(2027,2,15));
        societaService.inserisciNuovo(nuovaSocieta);

        Dipendente dipendente = new Dipendente("Alessandro", "Romano", LocalDate.of(2023, 3, 15), 34000, nuovaSocieta);
        dipendenteService.inserisciNuovo(dipendente);

        List<Progetto> progetti = List.of(
              //  new Progetto("Sistema Gestionale HR", "AlfaTech SPA", 12),
                new Progetto("App Mobile Banking", "Credito Digitale SRL", 8),
                new Progetto("Piattaforma E-Commerce", "Retail Solutions SRL", 10),
                new Progetto("Sistema di Monitoraggio IoT", "SmartFactory SPA", 6)
        );
        progetti.forEach(progetto -> progettoService.inserisciNuovo(progetto));

        //collego il dipendente ai progetti
        dipendenteService.collegaDipendenteAPiuProgetti(dipendente, progetti);

        // select di dipendente eager per vedere se li ha collegati
        System.out.println(dipendenteService.trovaPerIdEager(dipendente.getId()).getProgetti());
        if(dipendente.getProgetti().size() != 3) throw new Exception("TEST FALLITO: numero progetti del dipendente non corretto.");

        System.out.println("FINE TEST testAbbinamentoDipendenteAProgetti: CON SUCCESSO");
    }
    }
