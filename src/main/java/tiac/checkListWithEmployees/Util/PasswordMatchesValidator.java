package tiac.checkListWithEmployees.Util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tiac.checkListWithEmployees.entity.DTO.EmployeeDTO;

@Component
public class PasswordMatchesValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	EmployeeDTO newEmployee=(EmployeeDTO) target;
	if(!newEmployee.getPassword().equals(newEmployee.getConfirmPassword())){
		errors.reject("400","Password must match");}
	
	
	}
	
	
}

