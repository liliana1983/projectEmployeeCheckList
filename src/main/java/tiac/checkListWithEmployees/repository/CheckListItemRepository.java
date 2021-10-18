package tiac.checkListWithEmployees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.CheckListItemTemplate;

@Repository
public interface CheckListItemRepository extends JpaRepository<CheckListItemTemplate, Long>{
	
	List<CheckListItemTemplate> findAllByCheckListId(Long checkId);

	
}
