package app;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonaRepo extends CrudRepository<Persona, Integer> {
	
	public List<Persona> findAll();
	
	public Optional<Persona> findById(Integer id);
	
}