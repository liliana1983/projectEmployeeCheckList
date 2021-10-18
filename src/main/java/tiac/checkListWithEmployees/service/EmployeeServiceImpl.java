package tiac.checkListWithEmployees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.Util.Encryption;
import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.DTO.EmployeeDTO;
import tiac.checkListWithEmployees.exception.ResourceNotFoundException;
import tiac.checkListWithEmployees.repository.EmployeeRepository;
import tiac.checkListWithEmployees.repository.RoleRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Employee> findAllEmployees() {
		List<Employee> listOfEmployees = employeeRepo.findAll();
		return listOfEmployees;
	}

	@Override
	public Employee createEmployee(EmployeeDTO newEmployee) {
		Employee newE = new Employee();
		if (newEmployee.getPassword().equals(newEmployee.getConfirmPassword())) {
			newE.setName(newEmployee.getName());
			newE.setSurname(newEmployee.getSurname());
			newE.setUsername(newEmployee.getUsername());
			newE.setEmail(newEmployee.getEmail());
			newE.setPassword(Encryption.getPassEncoded(newEmployee.getPassword()));
			newE.setSocialSecurityNumber(newEmployee.getSocialSecurityNumber());
			newE.setPhoneNumber(newEmployee.getPhoneNumber());
			newE.setEducationLevel(newEmployee.getEducationLevel());
			employeeRepo.save(newE);
			return newE;
		}
		return null;
	}

	public Employee findByID(Long id) {
		if (!employeeRepo.existsById(id)) {
			throw new ResourceNotFoundException("Employee with Id=" + id + " doesn't exists!");
		}
		return employeeRepo.findById(id).get();
	}

	@Override
	public Employee changeEmployee(Long id, EmployeeDTO changedEmployee) {
		if (employeeRepo.existsById(id)) {
			Employee employee = employeeRepo.findById(id).get();
			employee.setName(Validation.setIfNotNull(employee.getName(), changedEmployee.getName()));
			employee.setSurname(Validation.setIfNotNull(employee.getSurname(), changedEmployee.getSurname()));
			employee.setEmail(Validation.setIfNotNull(employee.getEmail(), changedEmployee.getEmail()));
			employee.setPassword(Validation.setIfNotNull(employee.getPassword(), changedEmployee.getPassword()));
			employee.setPhoneNumber(
					Validation.setIfNotNull(employee.getPhoneNumber(), changedEmployee.getPhoneNumber()));
			employee.setUsername(Validation.setIfNotNull(employee.getUsername(), changedEmployee.getUsername()));
			employee.setEducationLevel(
					Validation.setIfNotNull(employee.getEducationLevel(), changedEmployee.getEducationLevel()));
			employeeRepo.save(employee);
			return employee;
		}
		return null;
	}

	@Override
	public Employee deleteEmployee(Long id) {
		if (employeeRepo.existsById(id)) {
			Employee delEmp= employeeRepo.findById(id).get();
			employeeRepo.delete(delEmp);
			return delEmp;
		}
		return null;
	}

	@Override
	public Employee LoggedInEmployee() {
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getPrincipal().toString();
		Employee employee= employeeRepo.findByUsername(username);
		return employee;
	}
}
