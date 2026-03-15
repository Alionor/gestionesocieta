package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.DipendenteRepository;
import it.prova.gestionesocieta.repository.ProgettoRepository;
import it.prova.gestionesocieta.repository.SocietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
@Transactional (readOnly = true)
public class ProgettoServiceImpl implements ProgettoService {

    @Autowired
    private ProgettoRepository progettoRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

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

    @Transactional
    public void collegaProgettoAPiuDipendenti(Long idProgetto, List<Dipendente> dipendenti) {
        Progetto progetto = progettoRepository.findById(idProgetto).orElse(null);
        dipendenti.forEach(dipendente -> {
            Dipendente dipendenteCurr = dipendenteRepository.getDipendenteEagerById(dipendente.getId());
            if (dipendenteCurr.getSocieta().getDataChiusura() != null &&
            dipendenteCurr.getSocieta().getDataChiusura().isBefore(LocalDate.now().plusMonths(progetto.getDurataInMesi())))
                throw new RuntimeException("La durata del progetto supera la data di chiusura della società.");
            progetto.getDipendenti().add(dipendenteCurr);
            dipendenteCurr.getProgetti().add(progetto); //SENZA QUESTA RIGA NON SALVA EFEFTTIVAMENTE I DIPENDENTI NEL PROGETTO E AL MOMENTO DEL TEST AL FETCHJOIN LA LISTA DEI DIPENDENTI NEL PROGETTO RISULTA VUOTA ANCHE SE SONO STATI EFFETTIVAMENTE AGGIUNTI DALLA RIGA SOPRA. PERCHè?
        });
        progettoRepository.save(progetto);
    }

    public Progetto trovaPerIdEager(Long idProgetto) {
        return progettoRepository.findEagerById(idProgetto);
    }
}
