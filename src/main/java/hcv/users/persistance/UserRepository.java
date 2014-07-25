package hcv.users.persistance;

import hcv.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author tdubravcevic
 */
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
}
