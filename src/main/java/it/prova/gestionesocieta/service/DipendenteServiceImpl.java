package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.DipendenteRepository;
import it.prova.gestionesocieta.repository.SocietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DipendenteServiceImpl implements DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private SocietaRepository societaRepository;


    public List<Dipendente> listAllDipendente() {
        return (List<Dipendente>) dipendenteRepository.findAll();
    }

    public Dipendente trovaPerId(Long id) {
        return dipendenteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Dipendente dipendenteInstance) {
        dipendenteRepository.save(dipendenteInstance);
    }

    @Transactional
    public void inserisciNuovo(Dipendente dipendenteInstance) {
        dipendenteRepository.save(dipendenteInstance);
    }

    @Transactional
    public void rimuovi(Long idDipendente) {
        dipendenteRepository.deleteById(idDipendente);
    }

/*    @Transactional
    public void collegaDipendenteASocieta(Long idSocieta, Long idDipendente) {
        Societa societa = societaRepository.findById(idSocieta).orElse(null);
        Dipendente dipendente = dipendenteRepository.findById(idDipendente).orElse(null);

        if (societa.getDataFondazione() != null
                && dipendente.getDataAssunzione() != null
                && societa.getDataFondazione().isAfter(dipendente.getDataAssunzione()))
            throw new RuntimeException("Errore: data assunzione precedente a data fondazione.");
        dipendenteRepository.linkEmployeesToSociety(idSocieta, idDipendente);
    }*/

    @Transactional
    public void inserisciNuovoConControllo(Long idSocieta, Dipendente dipendente) {
        Societa societa = societaRepository.findById(idSocieta).orElse(null);

        if (societa.getDataFondazione() != null
                && dipendente.getDataAssunzione() != null
                && societa.getDataFondazione().isAfter(dipendente.getDataAssunzione()))
            throw new RuntimeException("Errore: data assunzione precedente a data fondazione.");

        dipendenteRepository.save(dipendente);
    }

}
