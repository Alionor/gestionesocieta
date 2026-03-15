package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProgettoRepository  extends CrudRepository<Progetto, Long> {

    @EntityGraph (attributePaths = "dipendenti")
    Progetto findEagerById(Long idProgetto);

    @Query ("SELECT distinct p.cliente FROM Progetto p join p.dipendenti d where d.societa.id = :idSocieta")
    List<String> findClientiBySocieta(@Param("idSocieta") Long idSocieta);

    @Query ("SELECT p FROM Progetto p join p.dipendenti d where d.redditoAnnuoLordo > :ral")
    List<Progetto> findAllByDipendente_RalGreaterThan(@Param("ral") int ral);
}

