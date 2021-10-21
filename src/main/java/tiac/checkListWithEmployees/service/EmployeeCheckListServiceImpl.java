package tiac.checkListWithEmployees.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.TimeFrame;
import tiac.checkListWithEmployees.entity.DTO.EmployeeCheckListDTO;
import tiac.checkListWithEmployees.repository.CheckListItemRepository;
import tiac.checkListWithEmployees.repository.CheckListRepository;
import tiac.checkListWithEmployees.repository.EmployeeCheckListRepository;
import tiac.checkListWithEmployees.repository.EmployeeRepository;
import tiac.checkListWithEmployees.repository.TimeFrameRepository;

@Service
public class EmployeeCheckListServiceImpl implements EmployeeCheckListService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	EmployeeCheckListRepository employeeCheckRepo;
	@Autowired
	TimeFrameRepository timeRepo;

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	CheckListRepository checkRepo;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	CheckListItemRepository itemRepo;

	@Override
	public List<EmployeeCheckList> getAllEmployeeCheckLists() {
		return employeeCheckRepo.findAll();
	}
@Override
public List<EmployeeCheckList> listEmployee(Long id){
	return employeeCheckRepo.findAllByEmployeeId(id);
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
	public List<EmployeeCheckList> createEmployeeCheckList(Long checkId, Long employeeId) {
		if (!checkRepo.existsById(checkId))
			return null;
		if (!employeeRepo.existsById(employeeId))
			return null;
		List<EmployeeCheckList> employeeCheckList = new ArrayList<>();
		Employee employee = employeeRepo.findById(employeeId).get();
		CheckListTemplate checkList = checkRepo.findById(checkId).get();
		List<CheckListItemTemplate> itemList = itemRepo.findAllByCheckListId(checkId);
		for (CheckListItemTemplate list : itemList) {
			EmployeeCheckList emp = new EmployeeCheckList();
			emp.setCheckListTemplate(checkList);
			emp.setEmployee(employee);
			emp.setDescription(list.getDescription());
			emp.setTimeDropDown(list.getTimeDropdown().getName().toString());
			emp.setChecked(false);
			employeeCheckList.add(emp);
		}
		employeeCheckRepo.saveAll(employeeCheckList);

		return employeeCheckList;

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
		updated.setCheckListTemplate(Validation.setIfNotNull(updated.getCheckListTemplate(),
				changedEmployeeCheckList.getCheckListTemplate()));
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
	public EmployeeCheckList connectEmployeeAndCheckListWithEmployeeCheckList(EmployeeCheckListDTO newEmployeeCheckList,
			Long checkId, Long employeeId) {
		if (!checkRepo.existsById(checkId))
			return null;
		if (!employeeRepo.existsById(employeeId))
			return null;
		EmployeeCheckList employeeCheckList = new EmployeeCheckList();
		employeeCheckList.setDescription(newEmployeeCheckList.getDescription());
		CheckListTemplate checkList = checkRepo.findById(checkId).get();
		Employee employee = employeeRepo.findById(employeeId).get();
		employeeCheckList.setCheckListTemplate(checkList);
		employeeCheckList.setEmployee(employee);
		return employeeCheckRepo.save(employeeCheckList);

	}

	@Override
	public EmployeeCheckList setIfIsChecked(Long employeeCheckId) {
		Employee employee = employeeService.LoggedInEmployee();
		Long employeeId = employee.getId();
		if (employeeCheckRepo.existsById(employeeCheckId)) {
			EmployeeCheckList assignedEmployeeCheckList = employeeCheckRepo.findById(employeeCheckId).get();
			Long id = assignedEmployeeCheckList.getEmployee().getId();
			if (id.equals(employeeId)) {
				assignedEmployeeCheckList.setChecked(true);
				return employeeCheckRepo.save(assignedEmployeeCheckList);
			}
		}
		return null;
	}

	@Override
	public List<EmployeeCheckList> findByEmployee(Long employeeId) {
		Employee employee = employeeService.LoggedInEmployee();
		Long employeeGetId = employee.getId();
		if (employeeGetId.equals(employeeId)) {
			List<EmployeeCheckList> listForOneEmployee = employeeCheckRepo.findAllByEmployeeId(employeeId);
			return listForOneEmployee;
		}
		return null;
	}

	@Override
	public List<CheckListTemplate> getAllChecked() {
		List<Long> kompletLista = employeeCheckRepo.findAllByEmployeeIdAndCheckIdParamsNative(true);
		List<CheckListTemplate> checkedLists = checkRepo.findAllById(kompletLista);

		return checkedLists;
	}

	@Override
	public List<CheckListTemplate> getAllNotChecked() {
		List<Long> kompletLista = employeeCheckRepo.findAllByEmployeeIdAndCheckIdParamsNative(false);
		List<CheckListTemplate> unCheckedLists = checkRepo.findAllById(kompletLista);
		return unCheckedLists;
	}

	@Override
	public List<Employee> employeesWhoDidntCheckAll() {
		List<Long> employeeIds = employeeCheckRepo.findAllByEmployeeIdByEmployeeIdAndCheckIdParamsNative(false);
		List<Employee> employeeWithUncheckedLists = employeeRepo.findAllById(employeeIds);
		return employeeWithUncheckedLists;
	}

	@Override
	public List<String> findCheckedItemNames() {

		return employeeCheckRepo.seeItems(true);
	}

	@Override
	public List<String> findUncheckedItemNames() {

		return employeeCheckRepo.seeItems(false);
	}

	@Override
	public List<EmployeeCheckList> assignedCheckListsToEmployee(String username) {
		// TODO Auto-generated method stub
		return employeeCheckRepo.seeAssignedCheckListsByEmployee(username);
	}

	@Override
	public List<EmployeeCheckList> getTemplateForEmployee(String username, Boolean type) {
		 employeeCheckRepo.getAllEmployeeTemplates(username,type);
		 
		return employeeCheckRepo.getAllEmployeeTemplates(username,type);
	}

//6.	Omoguciti povlacenje svih template-a jednog tipa za zaposlenog i upis podataka u EmployeeCheckList tabelu sa default vrednostima
}
