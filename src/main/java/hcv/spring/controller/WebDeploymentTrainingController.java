package hcv.spring.controller;

import com.dropbox.core.DbxException;
import com.mysema.query.types.expr.BooleanExpression;
import hcv.data.repositories.TrainingRepository;
import hcv.manager.FileManager;
import hcv.manager.IFileManager;
import hcv.model.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
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

    @Autowired
    private IFileManager manager;

    public void executeQuery(String query) {
        //repository.findAll(query);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public
    @ResponseBody
    Long insert(@RequestBody Training training) throws IOException {

        return repository.save(training).getId();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public
    @ResponseBody
    String delete(@RequestBody Training training) throws IOException {

        repository.delete(training);
        return "OK";
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public
    @ResponseBody
    Long count() throws IOException {

        //FIXME count by username
        return repository.count();
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Training> filter(@RequestBody DatabaseFilter filter) throws IOException {

        List<Training> filtered = filterRepository(filter);

        sort(filter, filtered);

        return filtered;
    }

    @RequestMapping(value = "/byName", method = RequestMethod.POST)
    public
    @ResponseBody
    Training getOneByName(String name) throws IOException {

        return repository.findByName(name);
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    public
    @ResponseBody
    Training getOneById(Long id) throws IOException {

        return repository.findById(id);
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveFile(@RequestBody FileRequest data) throws IOException, DbxException {


        manager.storeData(data);

        return "OK";
    }

    @RequestMapping(value = "/loadFile", method = RequestMethod.POST)
    public
    @ResponseBody
    String loadFile(@RequestBody Long name) throws IOException, DbxException {

        Training training = new Training();
        training.setId(name);

        return FileUtils.readFileToString(manager.fetchFile(training));
	}

    private List<Training> filterRepository(DatabaseFilter filter) {

        Iterable<Training> result = repository.findAll(training.owner.eq(filter.getOwner()));

        List<Training> filtered = new ArrayList<Training>();

        for (Training training : result) {

            if(!filter.getLevels().isEmpty() && !CollectionUtils.containsAny(training.getLevels(), filter.getLevels())){
                continue;
            }

            if(!filter.getTags().isEmpty() && !CollectionUtils.containsAny(training.getTags(), filter.getTags())){
                continue;
            }

            filtered.add(training);
        }

		/*
        TrainingLevel c = alias(TrainingLevel.class, "level");
		for (String name : from()
				.list($(c.toString()))){
			System.out.println(name);
		}
		*/
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
            case DEFENSE:
                comparator = new Training.DefenseComparator();
                break;
            case OFFENSE:
                comparator = new Training.OffenseComparator();
                break;
            case GOALIE:
                comparator = new Training.GoalieComparator();
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
