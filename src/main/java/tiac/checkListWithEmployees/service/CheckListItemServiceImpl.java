package tiac.checkListWithEmployees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.TimeFrame;
import tiac.checkListWithEmployees.entity.DTO.CheckListItemTemplateDTO;
import tiac.checkListWithEmployees.repository.CheckListItemRepository;
import tiac.checkListWithEmployees.repository.CheckListRepository;
import tiac.checkListWithEmployees.repository.TimeFrameRepository;

@Service
public class CheckListItemServiceImpl implements CheckListItemService {
	@Autowired
	CheckListItemRepository itemRepo;
	@Autowired
	CheckListRepository checkRepo;
	@Autowired
	TimeFrameRepository timeRepo;

	@Override
	public List<CheckListItemTemplate> getAllItems() {
		return itemRepo.findAll();
	}

	@Override
	public CheckListItemTemplate remove(Long id) {

		if (!itemRepo.existsById(id))
			return null;
		CheckListItemTemplate item = itemRepo.findById(id).get();
		itemRepo.delete(item);
		return item;

	}

	@Override
	public CheckListItemTemplate createItem(CheckListItemTemplateDTO newItem) {
		CheckListItemTemplate item = new CheckListItemTemplate();
		item.setDescription(newItem.getDescription());
		itemRepo.save(item);
		return item;
	}

	@Override
	public CheckListItemTemplate changeItem(Long id, CheckListItemTemplateDTO changedItem) {
		if (!itemRepo.existsById(id)) {
			return null;
		}
		itemRepo.findById(id).get();
		CheckListItemTemplate item = itemRepo.findById(id).get();
		item.setDescription(Validation.setIfNotNull(item.getDescription(), changedItem.getDescription()));
		item.setCheckList(Validation.setIfNotNull(item.getCheckList(), changedItem.getCheckList()));
		itemRepo.save(item);
		return item;
	}

	@Override
	public CheckListItemTemplate findItem(Long id) {

		if (!itemRepo.existsById(id))
			return null;
		CheckListItemTemplate item = itemRepo.findById(id).get();
		return item;
	}

	@Override
	public CheckListItemTemplate addCheckListAndTime(Long checkId, Long itemId, Long timeId) {
		if (!itemRepo.existsById(itemId))
			return null;
		if (!checkRepo.existsById(checkId))
			return null;
		if (!timeRepo.existsById(timeId))
			return null;
		CheckListItemTemplate item = itemRepo.findById(itemId).get();
		CheckListTemplate checkList = checkRepo.findById(checkId).get();
		TimeFrame time = timeRepo.findById(timeId).get();
		item.setCheckList(checkList);
		item.setTimeDropdown(time);

		return itemRepo.save(item);

	}

}
