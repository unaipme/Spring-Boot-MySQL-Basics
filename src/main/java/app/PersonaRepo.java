package app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonaRepo extends CrudRepository<Persona, Integer> {
	
	public List<Persona> findAll();
	
}