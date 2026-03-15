package it.prova.gestionesocieta.service.test;

import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.service.DipendenteService;
import it.prova.gestionesocieta.service.ProgettoService;
import it.prova.gestionesocieta.service.SocietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatteriaTestSelect {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private SocietaService societaService;
    @Autowired
    private ProgettoService progettoService;

    public void testListe() {
        System.out.println("INIZIO testListe");
        //inserisco i record di società, dipendenti e progetti
        List<Societa> societaList = List.of(
                new Societa("TechFuture SPA", "Via Roma 10, Milano", LocalDate.of(1985, 3, 12), null),
                new Societa("OldIndustry SRL", "Via Torino 5, Torino", LocalDate.of(1975, 6, 20), LocalDate.of(2028, 5, 30))
        );
        societaList.forEach(societaService::inserisciNuovo);
        List<Dipendente> dipendenti = List.of(
                new Dipendente("Mario", "Rossi", LocalDate.of(2015, 5, 10), 32000, societaList.get(0)),
                new Dipendente("Luigi", "Verdi", LocalDate.of(2018, 3, 20), 28000, societaList.get(0)),
                new Dipendente("Anna", "Bianchi", LocalDate.of(2010, 7, 15), 35000, societaList.get(0)),
                new Dipendente("Paolo", "Neri", LocalDate.of(2019, 1, 10), 27000, societaList.get(1)),
                new Dipendente("Sara", "Conti", LocalDate.of(2016, 9, 5), 31000, societaList.get(1)),
                new Dipendente("Luca", "Ferrari", LocalDate.of(1970, 2, 2), 40000, societaList.get(0))
        );
        dipendenti.forEach(dipendenteService::inserisciNuovo);
        List<Progetto> progetti = List.of(
                new Progetto("Piattaforma E-Commerce", "Retail Corp", 10),
                new Progetto("Sistema IoT Industriale", "SmartFactory", 14),
                new Progetto("App Mobile Banking", "Credito Digitale", 8),
                new Progetto("Gestione Magazzino AI", "Logistica Italia", 6),
                new Progetto("CRM Cloud", "Business Solutions", 18)
        );
        progetti.forEach(progettoService::inserisciNuovo);

        //collego dipendenti a progetti e viceversa
        dipendenteService.collegaDipendenteAPiuProgetti(dipendenti.get(0),
                List.of(progetti.get(0), progetti.get(1)));
        dipendenteService.collegaDipendenteAPiuProgetti(dipendenti.get(1),
                List.of(progetti.get(1), progetti.get(2)));
        dipendenteService.collegaDipendenteAPiuProgetti(dipendenti.get(2),
                List.of(progetti.get(2), progetti.get(3)));
        dipendenteService.collegaDipendenteAPiuProgetti(dipendenti.get(3),
                List.of(progetti.get(3)));
        dipendenteService.collegaDipendenteAPiuProgetti(dipendenti.get(4),
                List.of(progetti.get(4)));
        dipendenteService.collegaDipendenteAPiuProgetti(dipendenti.get(5),
                List.of(progetti.get(0), progetti.get(4)));

        System.out.println("°°°°°°°°°°findClientiBySocieta°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        Societa societa = societaService.trovaPerId(1L);
        System.out.println(progettoService.trovaClientiDiProgettiBySocieta(societa));

        System.out.println("°°°°°°°°°°trovaSocietaPerProgettiMaggioriDiUnAnno°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println(societaService.trovaSocietaPerProgettiMaggioriDiUnAnno());

        System.out.println("°°°°°°°°°°trovaProgettiByDipendenteConRalMaggioreDi30k°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println(progettoService.trovaProgettiByDipendenteConRalMaggioreDi30k());

        System.out.println("°°°°°°°°°°trovaDipendenteLavorativamentePiuAnzianoDiSocietaFondatePrimaDel1990InProgettiConDurataMaggioreDi6Mesi°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println(dipendenteService.trovaDipendenteLavorativamentePiuAnzianoDiSocietaFondatePrimaDel1990InProgettiConDurataMaggioreDi6Mesi());

        System.out.println("°°°°°°°°°°trovaProgettiConDipendentiDiSocietaChiuse°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println(progettoService.trovaProgettiConDipendentiDiSocietaChiuse());

        System.out.println("°°°°°°°°°°trovaSocietaConDataFondazioneSuccessivaADataAssunzioneDipendente°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println(societaService.trovaSocietaConDataFondazioneSuccessivaADataAssunzioneDipendente());

        System.out.println("FINE testListe");
    }
}
