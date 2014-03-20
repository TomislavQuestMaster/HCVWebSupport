package hcv.data.repositories;

import hcv.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Tomo.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("select t from Training t where t.lastUpdate < ? and t.updatingDeviceName != ?")
    public List<Training> getUnsyncedTrainings(Long lastUpdate, String updatingDeviceName);
}