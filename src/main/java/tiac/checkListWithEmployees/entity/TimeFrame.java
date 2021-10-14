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

@Entity
public class TimeFrame {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "timeDropdown", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<CheckListItemTemplate> items;
	
	@OneToMany(mappedBy = "timeDropdown", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<EmployeeCheckList> employeeCheckLists;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
