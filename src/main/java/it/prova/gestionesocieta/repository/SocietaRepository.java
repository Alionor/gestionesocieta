package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Societa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocietaRepository  extends CrudRepository<Societa, Long> {

    Societa findByRagioneSociale(String ragioneSociale);

    @EntityGraph (attributePaths = "dipendenti")
    Societa findEagerById(Long id);

/*    @Modifying
    @Query("UPDATE Dipendente d SET d.societa.id = NULL WHERE d.societa.id = :societaId")
    void unlinkEmployeesFromSociety(@Param("societaId")Long idSocieta);*/

   @Query("SELECT s.ragioneSociale from Societa s join s.dipendenti d join d.progetti p where p.durataInMesi > :durata")
    List<String> findAllByProgetti_durataInMesiGreaterThan(@Param("durata") int durataInMesi);

}
