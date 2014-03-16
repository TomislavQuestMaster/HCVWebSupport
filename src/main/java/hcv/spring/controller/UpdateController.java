package hcv.spring.controller;

import hcv.spring.model.Data;
import hcv.spring.model.FetchRequest;
import hcv.spring.model.UpdateRequest;
import hcv.spring.model.UpdateResponse;
import hcv.spring.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Controller
public class UpdateController {

    @Autowired
    private IDataService IDataService;

    public void setIDataService(IDataService IDataService) {
        this.IDataService = IDataService;
    }

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
    public @ResponseBody List<Data> fetching(@RequestBody FetchRequest request) {

        List<Data> dataList = IDataService.getAllData();

        List<Data> result = new ArrayList<Data>();

        for(Data data : dataList){
            if(data.getLastUpdate()<request.getLastUpdate() && !data.getUpdatingDeviceName().equals(request.getDeviceName())){
                result.add(data);
            }
        }//TODO refactor spring data

        return result;
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
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