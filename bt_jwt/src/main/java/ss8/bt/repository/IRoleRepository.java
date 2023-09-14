package ss8.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss8.bt.model.entity.Role;
import ss8.bt.model.entity.RoleName;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
