package app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonaRest {
	
	@Autowired
	private PersonaRepo repo;
	
	@GetMapping
	public List<Persona> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Persona getOne(@PathVariable Integer id) {
		Optional<Persona> p = repo.findById(id);
		if (!p.isPresent()) {
			throw new NotFoundException("No person found with that ID");
		}
		return p.get();
	}
	
	@GetMapping("/{id}/group")
	public Grupo getGroup(@PathVariable Integer id) {
		Optional<Persona> p = repo.findById(id);
		if (!p.isPresent()) {
			throw new NotFoundException("No person was found with that ID");
		} else if (p.get().getGroup() == null) {
			throw new NotFoundException(String.format("The person with ID %d does not belong to any group", id));
		} else {
			return p.get().getGroup();
		}
	}
	
	@PostMapping(params={"firstName", "lastName"})
	public String create(@RequestParam(value="firstName") String firstName,
						@RequestParam(value="lastName") String lastName) {
		try {
			Persona p = new Persona(firstName, lastName);
			repo.save(p);
			return "OK";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
