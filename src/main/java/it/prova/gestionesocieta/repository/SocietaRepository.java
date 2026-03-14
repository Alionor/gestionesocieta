package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SocietaRepository  extends CrudRepository<Societa, Long> {

    Societa findByRagioneSociale(String ragioneSociale);

    @EntityGraph (attributePaths = "dipendenti")
    Societa findEagerById(Long id);

/*    @Modifying
    @Query("UPDATE Dipendente d SET d.societa.id = NULL WHERE d.societa.id = :societaId")
    void unlinkEmployeesFromSociety(@Param("societaId")Long idSocieta);*/

/*    @Modifying
    @Query ("UPDATE Dipendente d SET d.societa.id = :societaId WHERE d.id = :dipendeteId")
    void linkEmployeesToSociety(@Param("societaId") Long idSocieta, @Param ("dipendeteId") Long idDipendente);*/



}
