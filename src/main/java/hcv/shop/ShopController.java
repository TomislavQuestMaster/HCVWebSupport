package hcv.shop;

import static hcv.model.QTraining.*;
import static hcv.packages.model.QPackageItem.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import hcv.core.RequestHandler;
import hcv.core.manager.IFileManager;
import hcv.model.Response;
import hcv.model.user.QUser;
import hcv.packages.model.PackageItem;
import hcv.packages.persistance.PackageRepository;
import hcv.trainings.model.Training;
import hcv.trainings.persistance.TrainingRepository;
import hcv.users.model.User;
import hcv.users.persistance.UserRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShopController {

	@Autowired
	private TrainingRepository repository;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IFileManager manager;

	@RequestMapping(value = "/shop/trainings", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Training> getTrainingsFromShop() {

		return Lists.newArrayList(repository.findAll(training.owner.username.eq("powerUser")));
	}

	@RequestMapping(value = "/shop/trainings/unsorted", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Training> getUnsortedTrainingsFromShop() {

		List<Training> sortedTrainings = new ArrayList<Training>();
		for(PackageItem item : packageRepository.findAll(packageItem.owner.username.eq("powerUser"))){
			sortedTrainings.addAll(item.getTrainings());
		}

		return Lists.newArrayList(repository.findAll(training.owner.username.eq("powerUser").and(training.notIn(sortedTrainings))));
	}

	@RequestMapping(value = "/shop/download/{file_id}", method = RequestMethod.GET)
	public
	@ResponseBody
	FileSystemResource download(@PathVariable("file_id") Long fileId) throws FileNotFoundException {

		return new FileSystemResource(manager.fetchFileFromShop(fileId+".txt"));
	}

	@RequestMapping(value = "/shop/package/download/{file_id}", method = RequestMethod.GET)
	public
	@ResponseBody
	FileSystemResource downloadPackage(@PathVariable("file_id") Long fileId) throws FileNotFoundException {

		PackageItem item = packageRepository.findOne(fileId);
		manager.zipTrainings(item.getTrainings(), fileId + "");
		return new FileSystemResource(manager.fetchFileFromShop(fileId+".zip"));
	}

	@RequestMapping(value = "/shop/upload", method = RequestMethod.POST)
	public @ResponseBody
	Response upload(HttpServletRequest request) {

		RequestHandler handler = new RequestHandler();

		List<FileItem> items;
		try {
			items = handler.parseRequest(request);
		} catch (Exception e) {
			return new Response(5, "Parsing request on upload failed: " + e.getMessage());
		}

		Training received;
		try {
			received = getTrainingFromRequest(items.get(0));
		} catch (Exception e) {
			return new Response(5, "Parsing details from form failed: " + e.getMessage());
		}

		received = saveTrainingIfNotExists(received);

		try {
			manager.storeFile(received, items.get(1), "shop");
		} catch (Exception e) {
			return new Response(6, "Upload failed: " + e.getMessage());
		}

		return new Response(0, "Upload successful");
	}

	private Training saveTrainingIfNotExists(Training received) {

		if(repository.count(training.id.eq(received.getId()))==0){
			received = repository.save(received);
		}
		return received;
	}

	public Training getTrainingFromRequest(FileItem item) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String body = IOUtils.toString(item.getInputStream(), "UTF-8");
		return mapper.readValue(body, Training.class);
	}

	@RequestMapping(value = "/shop/packages", method = RequestMethod.GET)
	public
	@ResponseBody
	List<PackageItem> getPackagesFromShop() {

		return Lists.newArrayList(packageRepository.findAll(packageItem.owner.username.eq("powerUser")));
	}

	@RequestMapping(value = "/shop/package", method = RequestMethod.POST)
	@ResponseBody
	public Long addPackageToShop(@RequestBody PackageItem packageItem) {

	    User powerUser = userRepository.findOne(QUser.user.username.eq("powerUser"));
		packageItem.setOwner(powerUser);
		return packageRepository.save(packageItem).getId();
	}

	@RequestMapping(value = "/shop/package", method = RequestMethod.PUT)
	@ResponseBody
	public String updatePackage(@RequestBody PackageItem packageItem) {

		//PackageItem savedPackage = packageRepository.findOne(packageItem.getId());
		//savedPackage.setName(packageItem.getName());
		//savedPackage.setDescription(packageItem.getDescription());
		//savedPackage.setKeypoints(packageItem.getKeypoints());
		//savedPackage.setTrainings(packageItem.getTrainings());
		User powerUser = userRepository.findOne(QUser.user.username.eq("powerUser"));
		packageItem.setOwner(powerUser);
		packageRepository.save(packageItem);
		return "OK";
	}

	@RequestMapping(value = "/shop/package/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deletePackage(@PathVariable("id") Long id) {

		packageRepository.delete(id);
		return "OK";
	}

}
