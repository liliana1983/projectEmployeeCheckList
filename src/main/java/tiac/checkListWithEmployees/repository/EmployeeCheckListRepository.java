package tiac.checkListWithEmployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.EmployeeCheckList;

@Repository
public interface EmployeeCheckListRepository extends JpaRepository<EmployeeCheckList, Long>{

	
	
}
