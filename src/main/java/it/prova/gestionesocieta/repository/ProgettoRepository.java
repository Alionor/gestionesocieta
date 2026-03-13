package it.prova.gestionesocieta.repository;

import it.prova.gestionesocieta.model.Progetto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProgettoRepository  extends CrudRepository<Progetto, Long> {

}
