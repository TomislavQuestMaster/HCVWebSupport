package hcv.data.repositories;

import hcv.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by Tomo.
 */
public interface TrainingRepository extends JpaRepository<Training, Long>, QueryDslPredicateExecutor<Training> {

    @Query("select t from Training t where t.lastUpdate < ? and t.updatingDeviceName != ?")
    public List<Training> getUnsyncedTrainings(Long lastUpdate, String updatingDeviceName);

	public Training findById(Long id);

	public Training findByName(String name);

	public List<Training> findByOwner(String owner);

}