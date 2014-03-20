package hcv.data.repositories;

import hcv.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tdubravcevic
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}
