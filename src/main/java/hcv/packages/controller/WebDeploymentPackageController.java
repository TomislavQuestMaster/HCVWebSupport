package hcv.packages.controller;

import com.google.common.collect.Lists;
import hcv.packages.persistance.PackageRepository;
import hcv.packages.model.PackageItem;
import hcv.trainings.model.QTraining;
import hcv.trainings.model.Training;
import hcv.trainings.persistance.TrainingRepository;
import hcv.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static hcv.packages.model.QPackageItem.*;
import static hcv.packages.model.QPackageItem.packageItem;

/**
 * @author tdubravcevic
 */
@Controller
public class WebDeploymentPackageController {

	@Autowired
	private PackageRepository repository;

	@Autowired
	private TrainingRepository trainingRepository;

	@RequestMapping(value = "/insertPackage", method = RequestMethod.POST)
	public
	@ResponseBody
	Long insert(@RequestBody PackageItem packaging) throws IOException {

		ArrayList<Long> ids = new ArrayList<Long>();
		for (Training training : packaging.getTrainings()) {
			training = trainingRepository.save(training);
			ids.add(training.getId());
		}
		packaging.setTrainingsOrder(ids);

		return repository.save(packaging).getId();
	}

	@RequestMapping(value = "/removePackage", method = RequestMethod.POST)
	public
	@ResponseBody
	String delete(@RequestBody PackageItem packaging) throws IOException {

		repository.delete(packaging);
		return "OK";
	}

	@RequestMapping(value = "/getPackagesInGroup", method = RequestMethod.POST)
	public
	@ResponseBody
	List<PackageItem> filter(@RequestBody Long group, @RequestBody User owner) throws IOException {

		List<PackageItem> items = Lists.newArrayList(repository.findAll(packageItem.groupId.eq(group)
		                                                                                   .and(packageItem.owner.eq(
				                                                                                   owner))));

		for (PackageItem item : items) {
			List<Training> orderedTrainings = new ArrayList<Training>();
			for (Long id : item.getTrainingsOrder()) {
				orderedTrainings.add(item.getTrainings().get(item.getTrainings().indexOf(new Training(id))));
			}
			item.setTrainings(orderedTrainings);
		}

		return items;
	}

	@RequestMapping(value = "/allPackages", method = RequestMethod.POST)
	public
	@ResponseBody
	List<PackageItem> filter(@RequestBody User owner) throws IOException {

		List<PackageItem> items = Lists.newArrayList(repository.findAll(packageItem.owner.eq(owner)));

		for (PackageItem item : items) {
			List<Training> orderedTrainings = new ArrayList<Training>();
			for (Long id : item.getTrainingsOrder()) {
				orderedTrainings.add(item.getTrainings().get(item.getTrainings().indexOf(new Training(id))));
			}
			item.setTrainings(orderedTrainings);
		}

		return items;
	}

	@RequestMapping(value = "/trainings/{packageId}", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Training> filter(@PathVariable("packageId") Long packageId) throws IOException {

		PackageItem packageItem = repository.findOne(packageId);

		List<Training> items = Lists.newArrayList(trainingRepository.findAll(QTraining.training.in(packageItem.getTrainings())));

		List<Training> orderedTrainings = new ArrayList<Training>();
		for (Long id : packageItem.getTrainingsOrder()) {
			orderedTrainings.add(items.get(items.indexOf(new Training(id))));
		}

		return orderedTrainings;
	}

}
