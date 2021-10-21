package tiac.checkListWithEmployees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.EmployeeCheckList;

@Repository
public interface EmployeeCheckListRepository extends JpaRepository<EmployeeCheckList, Long> {

	public EmployeeCheckList findByEmployeeId(Long employeeId);

	boolean existsByEmployeeId(Long employeeId);

	public List<EmployeeCheckList> findAllByEmployeeId(Long employeeId);

	@Query(value = " SELECT distinct e.check_list_id from  employee_check_list e, employee_check_list e1"
			+ " where e.check_list_id=e1.check_list_id and e.employee_id=e1.employee_id and e.is_checked=:is_checked", nativeQuery = true)
	public List<Long> findAllByEmployeeIdAndCheckIdParamsNative(@Param("is_checked") Boolean isChecked);

	@Query(value = " SELECT distinct e.employee_id from  employee_check_list e, employee_check_list e1"
			+ " where e.check_list_id=e1.check_list_id and e.employee_id=e1.employee_id and e.is_checked=:is_checked", nativeQuery = true)
	public List<Long> findAllByEmployeeIdByEmployeeIdAndCheckIdParamsNative(@Param("is_checked") Boolean isChecked);

	@Query(value = "select distinct e.description from  employee_check_list e, employee_check_list e1, employee e2, check_list_item_template t, "
			+ "check_list_template c where e.check_list_id=e1.check_list_id and e.employee_id=e1.employee_id and c.id=t.check_list_id  and e.is_checked=:is_checked", nativeQuery = true)
	public List<String> seeItems(@Param("is_checked") Boolean isChecked);

	@Query(value = "select e.*"
			+ " from employee_check_list e, employee e2, check_list_template c where e.check_list_id=c.id  and c.type=1 and e2.username:username", nativeQuery=true)
	public List<EmployeeCheckList> seeAssignedCheckListsByEmployee(@Param("username") String username);

	@Query(value = "select e.id, e.description, e.is_checked, e.time_dropdown, e.check_list_id, e.employee_id"
			+ " from employee_check_list e, employee e2, check_list_template c where e.check_list_id=c.id  and c.type=:type and e2.username=:username", nativeQuery = true)
	public List<EmployeeCheckList> getAllEmployeeTemplates(@Param("username") String username,
			@Param("type") Boolean type);
}
