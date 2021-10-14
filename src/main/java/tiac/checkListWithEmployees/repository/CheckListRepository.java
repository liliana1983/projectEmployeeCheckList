package tiac.checkListWithEmployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.CheckListTemplate;

@Repository
public interface CheckListRepository extends JpaRepository<CheckListTemplate, Long>{

	
}
