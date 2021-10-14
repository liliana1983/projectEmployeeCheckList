package tiac.checkListWithEmployees.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CheckListItemTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String description;
	
	private boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_id")
	private CheckListTemplate checkList;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time_dropdown")
	private TimeFrame timeDropdown;
	

	public CheckListItemTemplate() {

	}

	public CheckListTemplate getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckListTemplate checkList) {
		this.checkList = checkList;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}