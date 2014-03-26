package hcv.spring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcv.core.RequestHandler;
import hcv.data.repositories.TrainingRepository;
import hcv.manager.IFileManager;
import hcv.model.FetchRequest;
import hcv.model.Response;
import hcv.model.Training;
import hcv.model.UpdateRequest;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
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

	@Autowired
	private IFileManager manager;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Response newUpdate(@RequestBody String body) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(body);

		Training received = mapper.convertValue(node.get("training"), Training.class);
		FetchRequest request =  mapper.convertValue(node.get("request"), FetchRequest.class);

        Training found = repository.findById(received.getId());
        if(found!=null &&
                !found.getUpdatingDeviceName().equals(request.getDeviceName()) &&
                found.getLastUpdate() > request.getLastUpdate()){
          received.setId(-1L);
          received.setName(received.getName()+"_"+request.getDeviceName());
        }

        received.setLastUpdate((new Date()).getTime());
        received = repository.save(received);
        return new Response(received.getId().intValue(), "Update successful");  //TODO different id returning
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Response delete(@RequestBody Training received) {

        repository.delete(received.getId());

        try {
            manager.deleteFile(received);
        } catch (Exception e) {
            return new Response(7, "Delete failed");
        }

        return new Response(0, "Delete successful");
    }

	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	public @ResponseBody List<Training> fetch(@RequestBody FetchRequest request) {

		return repository.getUnsyncedTrainings(request.getLastUpdate(), request.getDeviceName());
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Response upload(HttpServletRequest request) {

		RequestHandler handler = new RequestHandler();

		FileItem item;
		try {
			item = handler.parseRequest(request);
		} catch (Exception e) {
			return new Response(5, "Parsing request on upload failed: " + e.getMessage());
		}

		Training training = repository.findById(Long.decode(item.getName()));
        try {
            manager.storeFile(training, item);
        } catch (Exception e) {
            return new Response(6, "Upload failed: " + e.getMessage());
        }

        return new Response(0, "Upload successful");
	}

	@RequestMapping(value = "/download/{file_id}", method = RequestMethod.GET)
	public @ResponseBody FileSystemResource download(@PathVariable("file_id") Long fileId) throws FileNotFoundException {

		Training training = repository.findById(fileId);

        if(training == null){
            throw new FileNotFoundException("No file with uid: " + fileId);
        }

		return new FileSystemResource(manager.fetchFile(training));
	}

}