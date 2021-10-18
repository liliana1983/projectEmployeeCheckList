package tiac.checkListWithEmployees.service;

import java.util.ArrayList;
import java.util.List;

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
		//employeeCheckList.setDescription(newEmployeeCheckList.getDescription());
		//employeeCheckList.setChecked(newEmployeeCheckList.isChecked());
		Employee employee = employeeRepo.findById(employeeId).get();
		CheckListTemplate checkList = checkRepo.findById(checkId).get();
	//	employeeCheckList.setCheckListTemplate(checkList);
	//	employeeCheckList.setEmployee(employee);
		List<CheckListItemTemplate> itemList= itemRepo.findAllByCheckListId(checkId);

		for(CheckListItemTemplate  list: itemList){
			EmployeeCheckList emp= new EmployeeCheckList();
			 emp.setCheckListTemplate(checkList);
			 emp.setEmployee(employee);
			 emp.setDescription(list.getDescription());
			 
			emp.setTimeDropDown(list.getTimeDropdown().getName().toString());
			//emp.setTimeDropDown(list.getTimeDropdown());
			emp.setChecked(false);
			employeeCheckList.add(emp);}
			employeeCheckRepo.saveAll(employeeCheckList);
		
		//employeeCheckRepo.save(employeeCheckList);
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
	public EmployeeCheckList connectEmployeeAndCheckListWithEmployeeCheckList(EmployeeCheckListDTO newEmployeeCheckList, Long checkId, Long employeeId) {
		if (!checkRepo.existsById(checkId))
			return null;
		if (!employeeRepo.existsById(employeeId))
			return null;
		EmployeeCheckList employeeCheckList = new EmployeeCheckList();
		employeeCheckList.setDescription(newEmployeeCheckList.getDescription());
		//EmployeeCheckList employeeCheckList = employeeCheckRepo.findById(employeeCheckListId).get();
		CheckListTemplate checkList = checkRepo.findById(checkId).get();
		Employee employee = employeeRepo.findById(employeeId).get();
		employeeCheckList.setCheckListTemplate(checkList);
		employeeCheckList.setEmployee(employee);
		return employeeCheckRepo.save(employeeCheckList);

	}

	@Override
	public EmployeeCheckList setIfIsChecked(Long employeeCheckId, Boolean checked) {
		Employee employee = employeeService.LoggedInEmployee();
		Long employeeId= employee.getId();
		if(employeeCheckRepo.existsById(employeeCheckId)) {
			EmployeeCheckList assignedEmployeeCheckList = employeeCheckRepo.findById(employeeCheckId).get();
			Long id =assignedEmployeeCheckList.getEmployee().getId();
			if(id.equals(employeeId)) {
				assignedEmployeeCheckList.setChecked(checked);
				return employeeCheckRepo.save(assignedEmployeeCheckList);	
			}
		}return null;
	}// metoda gde radnik ima dozvolu da menja samo da li je checked ili ne. mora biti PATCH!

	@Override
	public List<EmployeeCheckList> getAllChecked() {
		String sql=" SELECT * from EmployeeCheckList e WHERE e.isChecked=:1";
		Query query= em.createQuery(sql);
		List<EmployeeCheckList> retVals= query.getResultList();
		return retVals; //trebalo bi da vrati sve sa true
		
		
		
	}


	@Override
	public List<EmployeeCheckList> getAllNotChecked() {
		String sql=" SELECT * from EmployeeCheckList e WHERE e.isChecked=:0";
		Query query= em.createQuery(sql);
		List<EmployeeCheckList> retVals= query.getResultList();
		return retVals; //trebalo bi da vrati sve koji nisu checked
	}

	@Override
	public List<EmployeeCheckList> findByEmployee(Long employeeId) {
		Employee employee = employeeService.LoggedInEmployee();
		Long employeeGetId= employee.getId();
		if(employeeGetId.equals(employeeId)) {
		List<EmployeeCheckList> listForOneEmployee=	employeeCheckRepo.findAllByEmployeeId(employeeId);
			return listForOneEmployee;
		}
		return null;
	}

	@Override
	public boolean checkAllFields() {
		List<EmployeeCheckList> allLists= employeeCheckRepo.findAll();
		return false;
	}

	//Stream.of(id, name).allMatch(Objects::isNull);

		// 4.2. Omoguciti stampanje check liste za kandidata u pdf
		// 4.3. Omoguciti da se vidi koje liste imaju sva polja chek-irana i liste koja
		// nemaju sva polja check-irana
//6.	Omoguciti povlacenje svih template-a jednog tipa za zaposlenog i upis podataka u EmployeeCheckList tabelu sa default vrednostima
}
