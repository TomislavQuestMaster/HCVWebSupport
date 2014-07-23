package hcv.data.repositories;

import hcv.model.calendar.CalendarItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Tomo.
 */
public interface CalendarRepository extends JpaRepository<CalendarItem, Long>, QueryDslPredicateExecutor<CalendarItem> {
}
