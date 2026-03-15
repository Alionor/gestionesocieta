package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Societa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocietaRepository  extends CrudRepository<Societa, Long> {

    Societa findByRagioneSociale(String ragioneSociale);

    @EntityGraph (attributePaths = "dipendenti")
    Societa findEagerById(Long id);

/*    @Modifying
    @Query("UPDATE Dipendente d SET d.societa.id = NULL WHERE d.societa.id = :societaId")
    void unlinkEmployeesFromSociety(@Param("societaId")Long idSocieta);*/

}
