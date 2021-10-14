package tiac.checkListWithEmployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Object findByUsername(String username);

	
	
}
