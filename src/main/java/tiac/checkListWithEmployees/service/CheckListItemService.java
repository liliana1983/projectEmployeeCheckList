package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.DTO.CheckListItemTemplateDTO;

public interface CheckListItemService {

	public List<CheckListItemTemplate> getAllItems();
	
	public CheckListItemTemplate remove(Long id);
	
	public CheckListItemTemplate createItem(CheckListItemTemplateDTO newItem);
	
	public CheckListItemTemplate changeItem(Long id, CheckListItemTemplateDTO changedItem);
	
	public CheckListItemTemplate findItem(Long id);
	
	public CheckListItemTemplate addCheckListAndTime(Long checkId,Long itemId, Long timeId);
	
}
