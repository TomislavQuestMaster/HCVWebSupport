package hcv.data.service;

import hcv.data.repositories.TrainingRepository;
import hcv.model.QTraining;
import hcv.model.Training;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static hcv.model.QTraining.*;

/**
 * @author tdubravcevic
 */
@Service
public class TrainingService {

	@Resource
	private TrainingRepository repository;

	@Transactional
	public Training create(Training training) {

		return repository.save(training);
	}

	@Transactional
	public Training update(Training updated){

		Training training = repository.findOne(updated.getId());

		training.setName(updated.getName());
		training.setLastUpdate(updated.getLastUpdate());
		training.setUpdatingDeviceName(updated.getUpdatingDeviceName());

		//repository.save(training);

		return training;
	}

	@Transactional
	public List<Training> getShoppingList(){

		repository.findAll(training.price.goe(0));

		return null;
	}

}
