package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.DTO.CheckListTemplateDTO;

public interface CheckListService {

	public List<CheckListTemplate> getAllCheckList();
	
	public CheckListTemplate removeCheckList(Long id);
	
	public CheckListTemplate createCheckList(CheckListTemplateDTO newItem);
	
	public CheckListTemplate changeCheckList(Long id, CheckListTemplateDTO changedCheckedList);
	
	public CheckListTemplate findCheckList(Long id);
}
