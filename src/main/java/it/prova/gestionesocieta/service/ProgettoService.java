package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Progetto;

import java.util.List;

public interface ProgettoService {
    public List<Progetto> listAllProgetto();

    public Progetto trovaPerId(Long id);

    public void aggiorna(Progetto progettoInstance);

    public void inserisciNuovo(Progetto progettoInstance);

    public void rimuovi(Long idDipendente);
}
