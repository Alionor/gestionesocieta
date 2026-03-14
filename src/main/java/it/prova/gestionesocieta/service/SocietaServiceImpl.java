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
    public void rimuovi(Long idSocieta) {
        societaRepository.deleteById(idSocieta);
    }

    @Transactional
    public void rimuoviConControllo(Long idSocieta) throws Exception {
        Societa societa = societaRepository.findEagerById(idSocieta);
        if (societa.getDipendenti().size() != 0) throw new RuntimeException("Impossibile rimuovere società: dipendenti presenti.");
        rimuovi(idSocieta);
    }

/*    @Transactional
    public void scollegaDipendentiDaSocieta(Long idSocieta) {
     societaRepository.unlinkEmployeesFromSociety(idSocieta);
    }*/

    @Transactional
    public void inserisciNuovoConControllo(Societa societaInstance) throws Exception {
        Societa societa = societaRepository.findByRagioneSociale(societaInstance.getRagioneSociale());
        if (societa != null) throw new RuntimeException("Societa già presente nella lista.");
        societaRepository.save(societaInstance);
    }
/*
    @Transactional
    public void collegaDipendenteASocieta(Long idSocieta, Long idDipendente) {
        societaRepository.linkEmployeesToSociety(idSocieta, idDipendente);
    }*/

    public Societa trovaPerRagioneSociale(String ragioneSociale) {
        return societaRepository.findByRagioneSociale(ragioneSociale);
    }


}
