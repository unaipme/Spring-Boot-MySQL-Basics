package app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GrupoRepo extends CrudRepository<Grupo, Integer> {
	
	public List<Grupo> findAll();
	
}
