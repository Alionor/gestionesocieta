package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;

import java.util.List;

public interface SocietaService {

    public List<Societa> listAllSocieta();

    public Societa trovaPerId(Long id);

    public void aggiorna(Societa societaInstance);

    public void inserisciNuovo(Societa societaInstance);

    public void rimuovi(Long idSocieta);
}
