package tiac.checkListWithEmployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.TimeFrame;

@Repository
public interface TimeFrameRepository extends JpaRepository<TimeFrame, Long>{

	
	
}
