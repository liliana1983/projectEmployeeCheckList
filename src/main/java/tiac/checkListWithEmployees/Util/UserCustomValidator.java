package tiac.checkListWithEmployees.Util;

import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.DTO.EmployeeDTO;
@Component
public class UserCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee employee=(Employee) target;
		EmployeeDTO newEmployee=(EmployeeDTO) target;
		if(!employee.getPassword().equals(newEmployee.getConfirmPassword()))
			errors.reject("400","passwords must match");
		
	}



}
