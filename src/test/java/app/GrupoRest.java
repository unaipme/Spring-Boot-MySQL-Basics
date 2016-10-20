package app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GrupoRest {
	
	@Autowired
	private PersonaRepo pRepo;
	
	@Autowired
	private GrupoRepo gRepo;
	
	@GetMapping
	public List<Grupo> getAll() {
		return gRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Grupo getOne(@PathVariable Integer id) {
		return gRepo.findOne(id);
	}
	
	@PostMapping(params={"name"})
	public String create(@RequestParam(value="name") String name) {
		try {
			Grupo g = new Grupo(name);
			gRepo.save(g);
			return "OK!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@PutMapping(params={"gid", "pid"})
	public String addToGroup(@RequestParam(value="gid") Integer gid,
							@RequestParam(value="pid") Integer pid) {
		try {
			Persona p = pRepo.findOne(pid);
			p.setGroup(gRepo.findOne(gid));
			pRepo.save(p);
			return "OK!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
