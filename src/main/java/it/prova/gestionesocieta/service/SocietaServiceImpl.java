package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = true)
public class SocietaServiceImpl implements SocietaService {

    @Autowired
    private SocietaRepository societaRepository;

    public List<Societa> listAllSocieta() {
        return (List<Societa>) societaRepository.findAll();
    }

    public Societa trovaPerId(Long id) {
        return societaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Societa societaInstance) {
        societaRepository.save(societaInstance);
    }

    @Transactional
    public void inserisciNuovo(Societa societaInstance) {
        societaRepository.save(societaInstance);
    }

    @Transactional
    public void rimuovi(Long idProgetto) {
        societaRepository.deleteById(idProgetto);
    }

}
