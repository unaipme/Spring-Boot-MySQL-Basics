package app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="people")
@TableGenerator(name="peoplegen", initialValue=0, allocationSize=1)
public class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="peoplegen")
	private Integer id;
	
	@Column(nullable=false, length=30)
	private String firstName;
	
	@Column(nullable=false, length=40)
	private String lastName;
	
	@JsonIgnore
	@ManyToOne(targetEntity=Grupo.class)
	private Grupo group;
	
	@Transient
	private String groupName;
	
	protected Persona() {}
	
	public Persona(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Grupo getGroup() {
		return group;
	}

	public void setGroup(Grupo group) {
		this.group = group;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getGroupName() {
		if (getGroup() != null)
			return getGroup().getName();
		else
			return null;
	}

	@Override
	public String toString() {
		return String.format("%s, %s (#%d)", lastName, firstName, id);
	}
	
}
