package tiac.checkListWithEmployees.entity.DTO;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;

import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.Employee;

public class EmployeeCheckListDTO {
	@AssertFalse
private boolean isChecked;
	
	@NotBlank(message = "Description must not be blank or null")
	private String description;


	private Employee employee;
	
	
	private CheckListTemplate checkListTemplate;


	public EmployeeCheckListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeCheckListDTO(boolean isChecked, String description, Employee employee,
			CheckListTemplate checkListTemplate) {
		super();
		this.isChecked = isChecked;
		this.description = description;
		this.employee = employee;
		this.checkListTemplate = checkListTemplate;
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
	
	
}