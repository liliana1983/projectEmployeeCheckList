package tiac.checkListWithEmployees.entity.DTO;

import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.TimeFrame;

public class CheckListItemTemplateDTO {

	private String description;

	private boolean active;

	private CheckListTemplate checkList;


	private TimeFrame timeDropdown;
	
	public CheckListItemTemplateDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public CheckListItemTemplateDTO(String description, boolean active, CheckListTemplate checkList,
			TimeFrame timeDropdown) {
		super();
		this.description = description;
		this.active = active;
		this.checkList = checkList;
		this.timeDropdown = timeDropdown;
	}
}
