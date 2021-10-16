package tiac.checkListWithEmployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tiac.checkListWithEmployees.entity.Role;
import tiac.checkListWithEmployees.entity.RoleEntity;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

RoleEntity findByName(Role roleName);
}
