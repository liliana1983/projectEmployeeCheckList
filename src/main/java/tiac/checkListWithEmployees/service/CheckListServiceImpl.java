package tiac.checkListWithEmployees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.DTO.CheckListTemplateDTO;
import tiac.checkListWithEmployees.repository.CheckListRepository;

@Service
public class CheckListServiceImpl implements CheckListService {
	@Autowired
	CheckListRepository checkRepo;

	@Override
	public List<CheckListTemplate> getAllCheckList() {
		// TODO Auto-generated method stub
		return checkRepo.findAll();
	}

	@Override
	public CheckListTemplate removeCheckList(Long id) {
		if (checkRepo.existsById(id)) {
			checkRepo.findById(id).get();
			CheckListTemplate checkList = checkRepo.findById(id).get();
			checkRepo.delete(checkList);
		}
		return null;
	}

	@Override
	public CheckListTemplate createCheckList(CheckListTemplateDTO newCheckList) {
		CheckListTemplate checkList = new CheckListTemplate();
		checkList.setDescription(newCheckList.getDescription());
		checkList.setType(newCheckList.isType());
		checkRepo.save(checkList);
		return checkList;
	}

	@Override
	public CheckListTemplate changeCheckList(Long id, CheckListTemplateDTO changedCheckList) {
		if (checkRepo.existsById(id)) {
			checkRepo.findById(id).get();
			CheckListTemplate checkList = checkRepo.findById(id).get();
			checkList.setDescription(
					Validation.setIfNotNull(checkList.getDescription(), changedCheckList.getDescription()));
			checkList.setType(Validation.setIfNotNull(checkList.isType(), changedCheckList.isType()));
			checkRepo.save(checkList);
			return checkList;
		}

		return null;
	}

	@Override
	public CheckListTemplate findCheckList(Long id) {
		if (checkRepo.existsById(id)) {
			return checkRepo.findById(id).get();
		}
		return null;
	}

}
