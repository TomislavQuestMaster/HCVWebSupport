package hcv.spring.controller;

import com.google.common.collect.Lists;
import hcv.data.repositories.PackageRepository;
import hcv.model.packaging.Package;
import hcv.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

import static hcv.model.packaging.QPackage.*;

/**
 * @author tdubravcevic
 */
@Controller
public class WebDeploymentPackageController {

	@Autowired
	private PackageRepository repository;

	@RequestMapping(value = "/insertPackage", method = RequestMethod.POST)
	public
	@ResponseBody
	Long insert(@RequestBody Package packaging) throws IOException {

		return repository.save(packaging).getId();
	}

	@RequestMapping(value = "/removePackage", method = RequestMethod.POST)
	public
	@ResponseBody
	String delete(@RequestBody Package packaging) throws IOException {

		repository.delete(packaging);
		return "OK";
	}

	@RequestMapping(value = "/getPackagesInGroup", method = RequestMethod.POST)
	public
	@ResponseBody
	List<Package> filter(@RequestBody Long group, @RequestBody User owner) throws IOException {
		//TODO get from request
		return Lists.newArrayList(repository.findAll(package$.groupId.eq(group).and(package$.owner.eq(owner))));
	}

	@RequestMapping(value = "/allPackages", method = RequestMethod.POST)
	public
	@ResponseBody
	List<Package> filter(@RequestBody User owner) throws IOException {

		return Lists.newArrayList(repository.findAll(package$.owner.eq(owner)));
	}


}
