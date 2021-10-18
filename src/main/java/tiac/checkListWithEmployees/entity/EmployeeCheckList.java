package tiac.checkListWithEmployees.entity;
import tiac.checkListWithEmployees.entity.TimeFrame;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class EmployeeCheckList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "is_checked")
	private boolean isChecked;
	
	@Column(name="time_dropdown")
	private String timeDropDown;
	
	@Column
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_id")
	private CheckListTemplate checkListTemplate;

	public EmployeeCheckList() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getTimeDropDown() {
		return timeDropDown;
	}

	public void setTimeDropDown(String timeDropDown) {
		this.timeDropDown = timeDropDown;
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

	public EmployeeCheckList(boolean isChecked, String timeDropDown, String description, Employee employee,
			CheckListTemplate checkListTemplate) {
		super();
		this.isChecked = isChecked;
		this.timeDropDown = timeDropDown;
		this.description = description;
		this.employee = employee;
		this.checkListTemplate = checkListTemplate;
	}

}