package tiac.checkListWithEmployees.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Integer id;
	@Column(name = "role_name")
	@Enumerated(EnumType.STRING)
	private Role name;
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Employee> employee = new ArrayList<>();

	public RoleEntity() {
		super();
	}

	public RoleEntity(Role roleName, List<Employee> employee) {
		super();
		name = roleName;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public Role getRoleName() {
		return name;
	}

	public void setRoleName(Role roleName) {
		name = roleName;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

}
