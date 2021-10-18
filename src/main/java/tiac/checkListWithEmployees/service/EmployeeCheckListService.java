package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.DTO.EmployeeCheckListDTO;

public interface EmployeeCheckListService {

	public List<EmployeeCheckList> getAllEmployeeCheckLists();

	public EmployeeCheckList deleteEmployeeCheckList(Long id);

	public List<EmployeeCheckList> createEmployeeCheckList(Long checkId, Long employeeId);

	public EmployeeCheckList changeEmployeeCheckList(Long id, EmployeeCheckListDTO changedEmployeeCheckList);

	public EmployeeCheckList findEmployeeCheckList(Long id);

	public EmployeeCheckList connectEmployeeAndCheckListWithEmployeeCheckList(EmployeeCheckListDTO newEmployeeCheckList, Long checkId, Long employeeId);
	
	public EmployeeCheckList setIfIsChecked(Long employeeCheckId,  Boolean checked);
	
	public List<EmployeeCheckList> getAllChecked();
	
	public List<EmployeeCheckList> getAllNotChecked();
	
	public List<EmployeeCheckList> findByEmployee(Long employeeId);
	
	public boolean checkAllFields();
	
	//public boolean containsIsCheckedTrue();

}
