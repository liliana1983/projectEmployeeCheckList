package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;

public interface EmployeeCheckListService {

	public List<EmployeeCheckList> getAllEmployeeCheckLists();

	public EmployeeCheckList deleteEmployeeCheckList(Long id);

	public List<EmployeeCheckList> createEmployeeCheckList(Long checkId, Long employeeId);

	public EmployeeCheckList findEmployeeCheckList(Long id);

	
	public EmployeeCheckList setIfIsChecked(Long employeeCheckId);
	
	public List<CheckListTemplate> getAllChecked();
	
	public List<CheckListTemplate> getAllNotChecked();
	
	public List<Employee> employeesWhoDidntCheckAll();
	
	public List<EmployeeCheckList> findByEmployee(Long employeeId);
	
	public List<String> findCheckedItemNames();
	
	public List<String> findUncheckedItemNames();
	
	public List<EmployeeCheckList> assignedCheckListsToEmployee(String username);
	
	public List<EmployeeCheckList> getTemplateForEmployee(Boolean type);
	
	public List<EmployeeCheckList> listEmployee(Long id);
	
	//public boolean checkAllFields();
	
	//public boolean containsIsCheckedTrue();

}
