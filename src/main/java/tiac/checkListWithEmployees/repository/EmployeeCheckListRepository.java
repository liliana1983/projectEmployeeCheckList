package tiac.checkListWithEmployees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.EmployeeCheckList;

@Repository
public interface EmployeeCheckListRepository extends JpaRepository<EmployeeCheckList, Long>{

	public EmployeeCheckList findByEmployeeId(Long employeeId);
	
	boolean existsByEmployeeId(Long employeeId);
	
	public List<EmployeeCheckList> findAllByEmployeeId(Long employeeId);
	
	//public List<EmployeeCheckList> findAllById
	
	
	
}
