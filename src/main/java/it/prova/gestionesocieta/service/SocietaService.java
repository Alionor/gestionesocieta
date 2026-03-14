package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Societa;

import java.util.List;

public interface SocietaService {

    public List<Societa> listAllSocieta();

    public Societa trovaPerId(Long id);

    public void aggiorna(Societa societaInstance);

    public void inserisciNuovo(Societa societaInstance);

    public void rimuovi(Long idSocieta);

    public void rimuoviConControllo(Long idSocieta) throws Exception;

    // public void scollegaDipendentiDaSocieta(Long idSocieta);

    public void inserisciNuovoConControllo(Societa societaInstance) throws Exception;

    public Societa trovaPerRagioneSociale(String ragioneSociale);

    public List<Societa> findByExample(Societa societa);

}
