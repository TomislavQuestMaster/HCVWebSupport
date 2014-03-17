package hcv.spring.controller;

import hcv.core.RequestHandler;
import hcv.spring.data.repositories.TrainingRepository;
import hcv.spring.model.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Controller
public class SynchronizationController {

    @Autowired
    private TrainingRepository repository;

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public @ResponseBody
    Response createOrUpdateTraining(@RequestBody Training training) {

        Long time = (new Date()).getTime();
        training.setLastUpdate(time);
        repository.save(training);

        Response response = new Response();
        response.setStatus(0);
        response.setMessage("Update successful");

        return response;
    }


    @RequestMapping(value="/fetch", method=RequestMethod.POST)
    public @ResponseBody List<Training> fetching(@RequestBody FetchRequest request) {

        return repository.getUnsyncedTrainings(request.getLastUpdate(), request.getDeviceName());
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestHandler handler = new RequestHandler();

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            handler.onRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"item\":\"fail: " + e.getMessage() + " \"}");
            return;
        }
        out.println("{\"item\":\"SUCCESS\"}");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile() {
        //YO HO HO
        return new FileSystemResource(new File("C:\\Users\\Tomo\\Desktop\\prvi.xml"));
    }

}