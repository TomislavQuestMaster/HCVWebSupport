package hcv.calendar.persistance;

import hcv.calendar.model.CalendarItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by Tomo.
 */
public interface CalendarRepository extends JpaRepository<CalendarItem, Long>, QueryDslPredicateExecutor<CalendarItem> {
}
