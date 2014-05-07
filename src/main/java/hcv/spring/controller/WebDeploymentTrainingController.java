package hcv.spring.controller;

import com.mysema.query.types.expr.BooleanExpression;
import hcv.data.repositories.TrainingRepository;
import hcv.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

import static com.mysema.query.collections.MiniApi.*;
import static com.mysema.query.alias.Alias.*;

import static hcv.model.QTraining.*;

/**
 * @author tdubravcevic
 */
@Controller
public class WebDeploymentTrainingController {

	@Autowired
	private TrainingRepository repository;

	public void executeQuery(String query){
		//repository.findAll(query);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody Training training) throws IOException {

		repository.save(training);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public void delete(@RequestBody Training training) throws IOException {

		repository.delete(training);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long count() throws IOException {

		//FIXME count by username
		return repository.count();
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody List<Training> filter(@RequestBody DatabaseFilter filter) throws IOException {

		List<Training> filtered = filterRepository(filter);

		sort(filter, filtered);

		return filtered;
	}

	@RequestMapping(value = "/byName", method = RequestMethod.POST)
	public @ResponseBody Training getOneByName(String name) throws IOException {

		return repository.findByName(name);
	}

	@RequestMapping(value = "/byId", method = RequestMethod.POST)
	public @ResponseBody Training getOneById(Long id) throws IOException {

		return repository.findById(id);
	}

	private List<Training> filterRepository(DatabaseFilter filter) {

		BooleanExpression expression = training.levels.eq(filter.getLevels());

		System.out.println(expression.stringValue().toString());

		Iterable<Training> result = repository.findAll();

		//ARRRGH
		/*
		TrainingLevel c = alias(TrainingLevel.class, "level");
		for (String name : from()
				.list($(c.toString()))){
			System.out.println(name);
		}
		*/

		//Iterable<Training> result = repository.findAll(training.tags.in((ArrayList<TrainingTag>) filter.getTags())
		//														.and(training.levels.in((ArrayList<TrainingLevel>) filter.getLevels())));

		List<Training> filtered = new ArrayList<Training>();
		addAll(filtered, result.iterator());
		return filtered;
	}

	private void sort(DatabaseFilter filter, List<Training> filtered) {

		Comparator<Training> comparator;

		switch (filter.getSortBy()) {
			case NAME:
				comparator = new Training.NameComparator();
				break;
			case STRESS:
				comparator = new Training.StressComparator();
				break;
			case TECHNICS:
				comparator = new Training.TechnicsComparator();
				break;
			case TACTICS:
				comparator = new Training.TacticsComparator();
				break;
			case FUN:
				comparator = new Training.FunComparator();
				break;
			default:
				throw new IllegalArgumentException("No such sort type: " + filter.getSortBy());
		}

		if (SortDirection.DESCENDING.equals(filter.getDirection())) {
			comparator = Collections.reverseOrder(comparator);
		}

		Collections.sort(filtered, comparator);
	}

	private void addAll(Collection collection, Iterator iterator) {

		while (iterator.hasNext()) {
			collection.add(iterator.next());
		}
	}
}
