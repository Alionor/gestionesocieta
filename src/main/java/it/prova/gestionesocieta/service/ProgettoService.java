package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;

import java.util.List;

public interface ProgettoService {
    public List<Progetto> listAllProgetto();

    public Progetto trovaPerId(Long id);

    public void aggiorna(Progetto progettoInstance);

    public void inserisciNuovo(Progetto progettoInstance);

    public void rimuovi(Long idDipendente);

    public void collegaProgettoAPiuDipendenti(Long idProgetto, List<Dipendente> dipendenti);

    public Progetto trovaPerIdEager(Long idProgetto);

    public List<String> trovaClientiDiProgettiBySocieta(Societa societa);

    public List<Progetto> trovaProgettiByDipendenteConRalMaggioreDi30k();

    public List<Progetto> trovaProgettiConDipendentiDiSocietaChiuse();
}
