package ss8.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss8.bt.model.entity.User;


import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail (String email);
}
