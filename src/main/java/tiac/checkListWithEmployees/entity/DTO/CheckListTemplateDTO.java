package tiac.checkListWithEmployees.entity.DTO;

import java.util.Set;
import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;

public class CheckListTemplateDTO {

	private boolean type;

	private String description;

	private Set<CheckListItemTemplate> items;

	private Set<EmployeeCheckList> employeeCheckLists;

	public CheckListTemplateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckListTemplateDTO(boolean type, String description, Set<CheckListItemTemplate> items,
			Set<EmployeeCheckList> employeeCheckLists) {
		super();
		this.type = type;
		this.description = description;
		this.items = items;
		this.employeeCheckLists = employeeCheckLists;
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
