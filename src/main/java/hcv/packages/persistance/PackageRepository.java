package hcv.packages.persistance;

import hcv.packages.model.PackageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author tdubravcevic
 */
public interface PackageRepository extends JpaRepository<PackageItem, Long>, QueryDslPredicateExecutor<PackageItem> {
}
