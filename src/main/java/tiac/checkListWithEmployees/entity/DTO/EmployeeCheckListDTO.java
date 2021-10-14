package tiac.checkListWithEmployees.entity.DTO;

import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.TimeFrame;

public class EmployeeCheckListDTO {
	
private boolean isChecked;
	
	
	private String description;

	
	private Employee employee;
	
	
	private CheckListTemplate checkListTemplate;
	
	
	private TimeFrame timeDropdown;


	public EmployeeCheckListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeCheckListDTO(boolean isChecked, String description, Employee employee,
			CheckListTemplate checkListTemplate, TimeFrame timeDropdown) {
		super();
		this.isChecked = isChecked;
		this.description = description;
		this.employee = employee;
		this.checkListTemplate = checkListTemplate;
		this.timeDropdown = timeDropdown;
	}


	public boolean isChecked() {
		return isChecked;
	}


	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public CheckListTemplate getCheckListTemplate() {
		return checkListTemplate;
	}


	public void setCheckListTemplate(CheckListTemplate checkListTemplate) {
		this.checkListTemplate = checkListTemplate;
	}


	public TimeFrame getTimeDropdown() {
		return timeDropdown;
	}


	public void setTimeDropdown(TimeFrame timeDropdown) {
		this.timeDropdown = timeDropdown;
	}
	
}
