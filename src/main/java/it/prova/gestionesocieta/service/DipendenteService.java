package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;

import java.util.List;

public interface DipendenteService {

    public List<Dipendente> listAllDipendente();

    public Dipendente trovaPerId(Long id);

    public void aggiorna(Dipendente dipendenteInstance);

    public void inserisciNuovo(Dipendente dipendenteInstance);

    public void rimuovi(Long idDipendente);
}
