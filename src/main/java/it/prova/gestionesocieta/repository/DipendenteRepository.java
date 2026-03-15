package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

    @Modifying
    @Query("UPDATE Dipendente d SET d.societa.id = :societaId WHERE d.id = :dipendeteId")
    void linkEmployeesToSociety(@Param("societaId") Long idSocieta, @Param ("dipendeteId") Long idDipendente);

    @Modifying
    @NativeQuery("INSERT into dipendente_progetto(dipendente_id, progetto_id) VALUES(:idDipendente, :idProgetto);")
    void linkProjectsToEmployee(@Param("idDipendente") Long idDipendente, @Param("idProgetto") Long idProgetto);

    @EntityGraph(attributePaths = "progetti")
    Dipendente getDipendenteEagerById(Long idDipendente);



}