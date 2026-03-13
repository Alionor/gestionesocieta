package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DipendenteServiceImpl implements DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

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

}
