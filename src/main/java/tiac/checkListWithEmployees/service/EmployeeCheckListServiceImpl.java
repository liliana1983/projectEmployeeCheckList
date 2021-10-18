package tiac.checkListWithEmployees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.DTO.EmployeeCheckListDTO;
import tiac.checkListWithEmployees.repository.CheckListRepository;
import tiac.checkListWithEmployees.repository.EmployeeCheckListRepository;
import tiac.checkListWithEmployees.repository.EmployeeRepository;

@Service
public class EmployeeCheckListServiceImpl implements EmployeeCheckListService {

	@Autowired
	EmployeeCheckListRepository employeeCheckRepo;

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	CheckListRepository checkRepo;

	@Override
	public List<EmployeeCheckList> getAllEmployeeCheckLists() {
		return employeeCheckRepo.findAll();
	}

	@Override
	public EmployeeCheckList deleteEmployeeCheckList(Long id) {
		if (employeeCheckRepo.existsById(id)) {
			employeeCheckRepo.findById(id).get();
			EmployeeCheckList delEmployeeCheckList = employeeCheckRepo.findById(id).get();
			employeeCheckRepo.delete(delEmployeeCheckList);
		}
		return null;
	}

	@Override
	public EmployeeCheckList createEmployeeCheckList(EmployeeCheckListDTO newEmployeeCheckList) {
		EmployeeCheckList employeeCheckList = new EmployeeCheckList();
		employeeCheckList.setDescription(newEmployeeCheckList.getDescription());
		employeeCheckList.setChecked(newEmployeeCheckList.isChecked());
		return employeeCheckRepo.save(employeeCheckList);

	}

	@Override
	public EmployeeCheckList changeEmployeeCheckList(Long id, EmployeeCheckListDTO changedEmployeeCheckList) {
		if (!employeeCheckRepo.existsById(id)) {
			return null;
		}
		employeeCheckRepo.findById(id).get();
		EmployeeCheckList updated = employeeCheckRepo.findById(id).get();
		updated.setDescription(
				Validation.setIfNotNull(updated.getDescription(), changedEmployeeCheckList.getDescription()));
		updated.setChecked(Validation.setIfNotNull(updated.isChecked(), changedEmployeeCheckList.isChecked()));
		return employeeCheckRepo.save(updated);
	}

	@Override
	public EmployeeCheckList findEmployeeCheckList(Long id) {
		if(employeeCheckRepo.existsById(id))
			employeeCheckRepo.findById(id).get();
		 EmployeeCheckList employeeList= employeeCheckRepo.findById(id).get();
		return employeeList;
	}

	@Override
	public EmployeeCheckList connectEmployeeAndCheckList(Long checkId, Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
