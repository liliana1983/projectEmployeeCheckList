package tiac.checkListWithEmployees.entity.DTO;

import java.util.Set;

import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.TimeName;

public class TimeFrameDTO {
	private TimeName name;

	private Set<CheckListItemTemplate> items;

	public TimeFrameDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeFrameDTO(TimeName name, Set<CheckListItemTemplate> items) {
		super();
		this.name = name;
		this.items = items;
	}

	public TimeName getName() {
		return name;
	}

	public void setName(TimeName name) {
		this.name = name;
	}

	public Set<CheckListItemTemplate> getItems() {
		return items;
	}

	public void setItems(Set<CheckListItemTemplate> items) {
		this.items = items;
	}

	
}
