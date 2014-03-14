package hcv.spring.controller;

import hcv.spring.model.Data;
import hcv.spring.model.UpdateRequest;
import hcv.spring.model.UpdateResponse;
import hcv.spring.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Controller
public class UpdateController {

    @Autowired
    private IDataService IDataService;

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public @ResponseBody UpdateResponse updating(@RequestBody UpdateRequest request) {

        Data data = new Data();
        data.setId(request.getId());
        data.setName(request.getName());
        data.setLastUpdate((new Date()).getTime());
        data.setUpdatingDeviceName(request.getDeviceName());

        IDataService.updateData(data);

        UpdateResponse response = new UpdateResponse();
        response.setStatus(0);
        response.setMessage("Update successful");

        return response;
    }

    @RequestMapping(value="/fetch", method=RequestMethod.POST)
    public @ResponseBody List<Data> fetching(@RequestBody UpdateRequest request) {

        return IDataService.getAllData();
    }
}

/*
* @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addTeamPage() {
        ModelAndView modelAndView = new ModelAndView("add-team-form");
        modelAndView.addObject("team", new Team());
        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingTeam(@ModelAttribute Team team) {

        ModelAndView modelAndView = new ModelAndView("home");
        //IDataService.addTeam(team);

        String message = "Team was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }
    */