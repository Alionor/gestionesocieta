package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.ProgettoRepository;
import it.prova.gestionesocieta.repository.SocietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional (readOnly = true)
public class ProgettoServiceImpl implements ProgettoService {

    @Autowired
    private ProgettoRepository progettoRepository;

    public List<Progetto> listAllProgetto() {
        return (List<Progetto>) progettoRepository.findAll();
    }

    public Progetto trovaPerId(Long id) {
        return progettoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Progetto progettoInstance) {
        progettoRepository.save(progettoInstance);
    }

    @Transactional
    public void inserisciNuovo(Progetto progettoInstance) {
        progettoRepository.save(progettoInstance);
    }

    @Transactional
    public void rimuovi(Long idSocieta) {
        progettoRepository.deleteById(idSocieta);
    }
}
