package it.prova.gestionesocieta.service;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = true)
public class SocietaServiceImpl implements SocietaService {

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Societa> findByExample(Societa societa) {

        StringBuilder queryBuilder = new StringBuilder("SELECT s FROM Societa s WHERE s.id = s.id");

        if(StringUtils.isNotEmpty(societa.getRagioneSociale())) queryBuilder.append(" and s.ragioneSociale like :ragioneSociale");
        if(StringUtils.isNotEmpty(societa.getIndirizzo())) queryBuilder.append(" and s.indirizzo like :indirizzo");
        if(StringUtils.isNotEmpty(societa.getDataFondazione() != null ? societa.getDataFondazione().toString() : null)) queryBuilder.append(" and s.dataFondazione = :dataFondazione");
        if(StringUtils.isNotEmpty(societa.getDataChiusura() != null ? societa.getDataChiusura().toString() : null)) queryBuilder.append(" and s.dataChiusura = :dataChiusura");

        TypedQuery<Societa> query = entityManager.createQuery(queryBuilder.toString(), Societa.class);

        if(StringUtils.isNotEmpty(societa.getRagioneSociale())) query.setParameter("ragioneSociale", "%" + societa.getRagioneSociale() + "%");
        if(StringUtils.isNotEmpty(societa.getIndirizzo())) query.setParameter("indirizzo", "%" + societa.getIndirizzo() + "%");
        if(StringUtils.isNotEmpty(societa.getDataFondazione() != null ? societa.getDataFondazione().toString() : null)) query.setParameter("dataFondazione", societa.getDataFondazione());
        if(StringUtils.isNotEmpty(societa.getDataChiusura() != null ? societa.getDataChiusura().toString() : null)) query.setParameter("dataChiusura", societa.getDataChiusura());

        return query.getResultList();
    }


}
