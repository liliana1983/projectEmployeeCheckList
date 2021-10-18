package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.DTO.EmployeeDTO;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	Employee createEmployee(EmployeeDTO newEmployee);

	Employee findByID(Long id);

	Employee changeEmployee(Long id, EmployeeDTO changedEmployee);
	
	Employee deleteEmployee(Long id);
	
	Employee LoggedInEmployee();

}
