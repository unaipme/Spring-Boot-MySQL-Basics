package app;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="groups")
@TableGenerator(name="grupogen", initialValue=0, allocationSize=1)
public class Grupo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="grupogen")
	private Integer id;
	
	@Column(name="group_name", nullable=false, length=40)
	private String name;
	
	@OneToMany(mappedBy="group")
	private List<Persona> members;
	
	protected Grupo() {}
	
	public Grupo(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Persona> getMembers() {
		return members;
	}

	public void setMembers(List<Persona> members) {
		this.members = members;
	}
	
}
