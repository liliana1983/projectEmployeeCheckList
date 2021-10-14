package tiac.checkListWithEmployees.entity.DTO;

import java.util.Set;

import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;

public class TimeFrameDTO {
	private String name;

	private Set<CheckListItemTemplate> items;

	private Set<EmployeeCheckList> employeeCheckLists;

	public TimeFrameDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeFrameDTO(String name, Set<CheckListItemTemplate> items, Set<EmployeeCheckList> employeeCheckLists) {
		super();
		this.name = name;
		this.items = items;
		this.employeeCheckLists = employeeCheckLists;
	}

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
