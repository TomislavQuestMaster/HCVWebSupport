package hcv.spring.controller;

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
import java.util.Date;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Controller
public class SynchronizationController {

	@Autowired
	private TrainingRepository repository;

	//@Autowired
	//private IFileManager manager;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Response update(@RequestBody Training received) {

		received.setLastUpdate((new Date()).getTime());
		received = repository.save(received);

		return new Response(received.getId().intValue(), "Update successful");  //TODO different id returning
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
			return new Response(5, "Upload failed: " + e.getMessage());
		}

		Training training = repository.getOne(Long.decode(item.getName()));
		//manager.storeFile(training, item);

		return new Response(0, "Upload successful");
	}

	@RequestMapping(value = "/download/{file_id}", method = RequestMethod.GET)
	public @ResponseBody FileSystemResource download(@PathVariable("file_id") Long fileId) {

		//Training training = repository.findOne(fileId);
		//return new FileSystemResource(manager.fetchFile(training));
		return null;
	}

}