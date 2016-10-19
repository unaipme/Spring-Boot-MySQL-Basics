package app;

import java.util.List;

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
		return repo.findOne(id);
	}
	
	@GetMapping("/{id}/group")
	public Grupo getGroup(@PathVariable Integer id) {
		return repo.findOne(id).getGroup();
	}
	
	@PostMapping(params={"firstName", "lastName"})
	public String create(@RequestParam(value="firstName") String firstName,
						@RequestParam(value="lastName") String lastName) {
		try {
			Persona p = new Persona(firstName, lastName);
			System.out.println(p);
			repo.save(p);
			return "OK";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
