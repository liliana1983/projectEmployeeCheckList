package tiac.checkListWithEmployees.entity.DTO;

import java.util.Set;

import tiac.checkListWithEmployees.entity.CheckListItemTemplate;

public class TimeFrameDTO {
	private String name;

	private Set<CheckListItemTemplate> items;

	public TimeFrameDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public TimeFrameDTO(String name, Set<CheckListItemTemplate> items) {
		super();
		this.name = name;
		this.items = items;
	}
}
