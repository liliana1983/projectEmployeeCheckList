package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.DTO.EmployeeCheckListDTO;

public interface EmployeeCheckListService {

	public List<EmployeeCheckList> getAllEmployeeCheckLists();

	public EmployeeCheckList deleteEmployeeCheckList(Long id);

	public EmployeeCheckList createEmployeeCheckList(EmployeeCheckListDTO newEmployeeCheckList);

	public EmployeeCheckList changeEmployeeCheckList(Long id, EmployeeCheckListDTO changedEmployeeCheckList);

	public EmployeeCheckList findEmployeeCheckList(Long id);

	public EmployeeCheckList connectEmployeeAndCheckListWithEmployeeCheckList(Long checkId, Long employeeId,
			Long employeeCheckListId);

}
