package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;

import java.util.List;
import java.util.Optional;

public interface DipendenteService {

    public List<Dipendente> listAllDipendente();

    public Dipendente trovaPerId(Long id);

    public void aggiorna(Dipendente dipendenteInstance);

    public void inserisciNuovo(Dipendente dipendenteInstance);

    public void rimuovi(Long idDipendente);

    //public void collegaDipendenteASocieta(Long idSocieta, Long idDipendente);

    public void inserisciNuovoConControllo(Long idSocieta, Dipendente dipendente);

    public void collegaDipendenteAPiuProgetti(Dipendente dipendente, List<Progetto> progetti);

    public Dipendente trovaPerIdEager(Long idDipendente);

    public Optional<Dipendente> trovaDipendenteLavorativamentePiuAnzianoDiSocietaFondatePrimaDel1990InProgettiConDurataMaggioreDi6Mesi();


    }
