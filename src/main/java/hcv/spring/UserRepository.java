package hcv.spring;

import hcv.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tdubravcevic
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
