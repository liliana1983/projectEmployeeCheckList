package tiac.checkListWithEmployees.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EmployeeCheckList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "is_checked")
	private boolean isChecked;
	
	@Column
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_id")
	private CheckListTemplate checkListTemplate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time_dropdown")
	private TimeFrame timeDropdown;
	
	public EmployeeCheckList() {

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


	public Long getId() {
		return id;
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
	
	
	
	
	

}
