package hcv.users.persistance;

import hcv.users.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author tdubravcevic
 */
public interface ClubRepository extends JpaRepository<Club, Long>, QueryDslPredicateExecutor<Club> {
}
