package tiac.checkListWithEmployees.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class EmployeeCheckList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "is_checked", columnDefinition = "boolean default false")
	private boolean isChecked;

	@Transient
	private String done = this.isChecked ? "done" : "not done";

	@Column(name = "time_dropdown")
	private String timeDropDown;

	@Column
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_id")
	private CheckListTemplate checkListTemplate;

	public EmployeeCheckList(boolean isChecked, String done, String timeDropDown, String description, Employee employee,
			CheckListTemplate checkListTemplate) {
		super();
		this.isChecked = isChecked;
		this.done = done;
		this.timeDropDown = timeDropDown;
		this.description = description;
		this.employee = employee;
		this.checkListTemplate = checkListTemplate;
	}

}