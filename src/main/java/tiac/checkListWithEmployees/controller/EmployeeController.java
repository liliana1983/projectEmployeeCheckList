package tiac.checkListWithEmployees.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tiac.checkListWithEmployees.Util.PasswordMatchesValidator;
import tiac.checkListWithEmployees.Util.UserCustomValidator;
import tiac.checkListWithEmployees.Util.Validation;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.DTO.EmployeeDTO;
import tiac.checkListWithEmployees.service.EmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private UserCustomValidator userCustomValidator;

	@Autowired
	private Validator[] usernameValidator;

	@Autowired
	private PasswordMatchesValidator passwordValidator;

	@InitBinder("user")
	protected void initUserBinder(WebDataBinder binder) {
		binder.addValidators(userCustomValidator);
	}

	@InitBinder("username")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(usernameValidator);
	}

	@InitBinder("password")
	protected void initPasswordBinder(WebDataBinder binder) {
		binder.addValidators(passwordValidator);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		List<EmployeeDTO> employees = employeeService.findAllEmployees().stream()
				.map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);

	}

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO newEmployee, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(Validation.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		Employee newBe = employeeService.createEmployee(newEmployee);
		EmployeeDTO employeeResponse = modelMapper.map(newBe, EmployeeDTO.class);

		return new ResponseEntity<EmployeeDTO>(employeeResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findEmployeeById(@PathVariable Long id, BindingResult result) {
		Employee employee = employeeService.findByID(id);
		EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
		return new ResponseEntity<EmployeeDTO>(employeeResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> changeEmployee(@PathVariable Long id, EmployeeDTO changeEmp) {
		Employee employee = employeeService.changeEmployee(id, changeEmp);
		EmployeeDTO employeeResponse = modelMapper.map(employee, EmployeeDTO.class);
		return new ResponseEntity<EmployeeDTO>(employeeResponse, HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id,BindingResult result){
		if (result.hasErrors()) {
			return new ResponseEntity<>(Validation.createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
