package hcv.spring.controller;

import hcv.core.UploadHandler;
import hcv.model.FetchRequest;
import hcv.model.Response;
import hcv.model.Training;
import hcv.data.repositories.TrainingRepository;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
	Response createOrUpdateTraining(@RequestBody
									Training training) {

        Long time = (new Date()).getTime();
        training.setLastUpdate(time);
        repository.save(training);

        Response response = new Response();
        response.setStatus(0);
        response.setMessage("Update successful");

        return response;
    }


    @RequestMapping(value="/fetch", method=RequestMethod.POST)
    public @ResponseBody List<Training> fetching(@RequestBody
													 FetchRequest request) {

        return repository.getUnsyncedTrainings(request.getLastUpdate(), request.getDeviceName());
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    protected @ResponseBody Response upload(HttpServletRequest request) throws ServletException, IOException {

        UploadHandler handler = new UploadHandler();

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        try {
            handler.onRequest(request);
        } catch (Exception e) {
            return new Response(5, "Upload failed: " +  e.getMessage());
        }
		return new Response(0, "Upload successful");
    }

    @RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        return new FileSystemResource(new File("C:\\Users\\tdubravcevic\\Downloads\\HCV\\"+fileName+".xml"));
    }

}