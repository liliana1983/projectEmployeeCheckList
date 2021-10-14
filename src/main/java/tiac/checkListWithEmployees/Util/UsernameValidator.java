package tiac.checkListWithEmployees.Util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tiac.checkListWithEmployees.repository.EmployeeRepository;

@Component
public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
		if (employeeRepository.findByUsername(username) == null && !username.isEmpty()) {
			if (employeeRepository.findByUsername(username) != null) {
				constraintValidatorContext.disableDefaultConstraintViolation();
				constraintValidatorContext.buildConstraintViolationWithTemplate("Username must be provided")
						.addConstraintViolation();
				return false;
			}
			return true;
		} else {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(
					"Username should be unique. '" + username + "' is already taken").addConstraintViolation();
			return false;
		}
	}

}
