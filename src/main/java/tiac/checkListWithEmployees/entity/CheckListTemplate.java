package tiac.checkListWithEmployees.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class CheckListTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private boolean type;
	
	@Column
	private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "checkList", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<CheckListItemTemplate> items;

	@OneToMany(mappedBy = "checkListTemplate", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<EmployeeCheckList> employeeCheckLists;
	
	public CheckListTemplate(boolean type, String description) {
		this.type = type;
		this.description = description;
	}

	public CheckListTemplate() {
		super();
	}

	public Set<CheckListItemTemplate> getItems() {
		return items;
	}

	public void setItems(Set<CheckListItemTemplate> items) {
		this.items = items;
	}

	public Set<EmployeeCheckList> getEmployeeCheckLists() {
		return employeeCheckLists;
	}

	public void setEmployeeCheckLists(Set<EmployeeCheckList> employeeCheckLists) {
		this.employeeCheckLists = employeeCheckLists;
	}

	public Long getId() {
		return id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
