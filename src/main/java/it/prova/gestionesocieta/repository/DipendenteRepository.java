package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {
}