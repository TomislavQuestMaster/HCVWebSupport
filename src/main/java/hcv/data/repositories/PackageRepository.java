package hcv.data.repositories;

import hcv.model.packaging.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author tdubravcevic
 */
public interface PackageRepository extends JpaRepository<Package, Long>, QueryDslPredicateExecutor<Package> {
}
