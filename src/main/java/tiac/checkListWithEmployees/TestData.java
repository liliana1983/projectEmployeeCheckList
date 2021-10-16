package tiac.checkListWithEmployees;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tiac.checkListWithEmployees.service.EmployeeService;

//@Component
public class TestData {
	/*
	 * @Autowired EmployeeService employeeService;
	 */
	
	@PostConstruct
	public void init(){}
}
