package hcv.spring.controller;

import hcv.data.repositories.CalendarRepository;
import hcv.model.FetchRequest;
import hcv.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Tomo.
 */
@Controller
public class CalendarSynchronizationController {

    @Autowired
    private CalendarRepository repository;

    @RequestMapping(value = "/calendar/fetch", method = RequestMethod.POST)
    public @ResponseBody
    List<Training> fetch(@RequestBody FetchRequest request) {

        //return repository.findAll(QCalendar.);
        //return repository.getUnsyncedTrainings(request.getLastUpdate(), request.getDeviceName());
        //return repository.findByOwner(request.getUser());
        return null;
    }

}
