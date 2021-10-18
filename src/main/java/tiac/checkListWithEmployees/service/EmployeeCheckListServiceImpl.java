package tiac.checkListWithEmployees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.Employee;
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
		updated.setCheckListTemplate(Validation.setIfNotNull(updated.getCheckListTemplate(), changedEmployeeCheckList.getCheckListTemplate()));
		updated.setEmployee(Validation.setIfNotNull(updated.getEmployee(), changedEmployeeCheckList.getEmployee()));
		return employeeCheckRepo.save(updated);
	}

	@Override
	public EmployeeCheckList findEmployeeCheckList(Long id) {
		if (employeeCheckRepo.existsById(id)) {
			employeeCheckRepo.findById(id).get();
			EmployeeCheckList employeeList = employeeCheckRepo.findById(id).get();
			return employeeList;
		}
		return null;
	}

	@Override
	public EmployeeCheckList connectEmployeeAndCheckListWithEmployeeCheckList(Long checkId, Long employeeId,
			Long employeeCheckListId) {
		if (!employeeCheckRepo.existsById(employeeCheckListId))
			return null;
		if (!checkRepo.existsById(checkId))
			return null;
		if (!employeeRepo.existsById(employeeId))
			return null;
		EmployeeCheckList employeeCheckList = employeeCheckRepo.findById(employeeCheckListId).get();
		CheckListTemplate checkList = checkRepo.findById(checkId).get();
		Employee employee = employeeRepo.findById(employeeId).get();
		employeeCheckList.setCheckListTemplate(checkList);
		employeeCheckList.setEmployee(employee);
		return employeeCheckRepo.save(employeeCheckList);

	}// ista ova metoda za CEO gde prosledjuje username radnika
		// metoda gde radnik ima dozvolu da menja samo da li je checked ili ne
		// metoda koja izlistava sve checked ili ne checked
		// metoda koja izlistava sve liste vezane za jednog radnika
		// 4.1. Omoguciti izlistavanje check lista po kandidatu
		// 4.2. Omoguciti stampanje check liste za kandidata u pdf
		// 4.3. Omoguciti da se vidi koje liste imaju sva polja chek-irana i liste koja
		// nemaju sva polja check-irana
//6.	Omoguciti povlacenje svih template-a jednog tipa za zaposlenog i upis podataka u EmployeeCheckList tabelu sa default vrednostima
}
