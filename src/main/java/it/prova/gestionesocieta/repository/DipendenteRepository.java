package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

    @Modifying
    @Query("UPDATE Dipendente d SET d.societa.id = :societaId WHERE d.id = :dipendeteId")
    void linkEmployeesToSociety(@Param("societaId") Long idSocieta, @Param ("dipendeteId") Long idDipendente);

    @Modifying
    @NativeQuery("INSERT into dipendente_progetto(dipendente_id, progetto_id) VALUES(:idDipendente, :idProgetto);")
    void linkProjectsToEmployee(@Param("idDipendente") Long idDipendente, @Param("idProgetto") Long idProgetto);

    @EntityGraph(attributePaths = "progetti")
    Dipendente getDipendenteEagerById(Long idDipendente);

    @NativeQuery ("SELECT d.* FROM dipendente d join societa s on s.id = d.societa_id join dipendente_progetto dp on dp.dipendente_id = d.id join progetto p on dp.progetto_id = p.id where s.datafondazione < :dataFondazione and p.duratainmesi >= :durata ORDER BY d.dataassunzione LIMIT 1")
    Optional<Dipendente> getDipendenteWhoHasWorkedLongerInSocietaFoundedBeforeDataWorkingOnProgettoLastingMoreThan(@Param("dataFondazione")LocalDate dataFondazione, @Param("durata") int durataInMesi);
    //dipendente più anziano  - lavorativamente parlando – delle società
    // fondate prima del 1990 e che lavora su progetto che dura almeno 6 mesi.

}