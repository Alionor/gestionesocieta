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
public class BatteriaTestProgetto {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private SocietaService societaService;
    @Autowired
    private ProgettoService progettoService;

    public void testAbbinamentoProgettoAPiuDipendenti() throws Exception {
        System.out.println("INIZIO TEST testAbbinamentoProgettoAPiuDipendenti");
        //inserisco le società, i dipendenti e i progetti

        Societa nuovaSocieta1 = new Societa("NextGen Solutions SRL", "Via Milano 25, Bologna", LocalDate.of(2018, 5, 10), LocalDate.of(2027,2,15));
        societaService.inserisciNuovo(nuovaSocieta1);
        Societa nuovaSocieta2 = new Societa("Gianfranco SRL", "Via Milano 25, Bologna", LocalDate.of(2018, 5, 10), null);
        societaService.inserisciNuovo(nuovaSocieta2);

        //Il secondo progetto manda in eccezione il sistema
        Progetto progetto = new Progetto("App Mobile Banking", "Credito Digitale SRL", 8);
        //Progetto progetto = new Progetto("App Mobile Banking", "Credito Digitale SRL", 12);
        progettoService.inserisciNuovo(progetto);

        List<Dipendente> dipendenti = List.of(
                new Dipendente("Alessandro", "Romano", LocalDate.of(2023, 3, 15), 34000, nuovaSocieta1),
                new Dipendente("Marco", "Bianchi", LocalDate.of(2021, 6, 10), 38000, nuovaSocieta1),
                new Dipendente("Giulia", "Ferrari", LocalDate.of(2022, 9, 5), 36000, nuovaSocieta2),
                new Dipendente("Sara", "Conti", LocalDate.of(2020, 1, 20), 41000, nuovaSocieta2)
        );
        dipendenti.forEach(d -> dipendenteService.inserisciNuovo(d));

        //collego i dipendenti ai progetti
        progettoService.collegaProgettoAPiuDipendenti(progetto.getId(), dipendenti);

        // select di progetto eager per vedere se li ha collegati
        progetto = progettoService.trovaPerIdEager(progetto.getId());
        System.out.println(progetto.getDipendenti());
        if(progetto.getDipendenti().size() != 4) throw new Exception("TEST testAbbinamentoProgettoAPiuDipendenti FALLITO: numero dipendenti nel progetto non corretto.");

        System.out.println("FINE TEST testAbbinamentoProgettoAPiuDipendenti: CON SUCCESSO");
    }

}
