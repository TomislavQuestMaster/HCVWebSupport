package hcv.packages.controller;

import com.google.common.collect.Lists;
import hcv.packages.model.QPackageItem;
import hcv.packages.persistance.PackageRepository;
import hcv.packages.model.PackageItem;
import hcv.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

import static hcv.model.packaging.QPackage.*;
import static hcv.packages.model.QPackageItem.*;

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
	Long insert(@RequestBody PackageItem packaging) throws IOException {

		return repository.save(packaging).getId();
	}

	@RequestMapping(value = "/removePackage", method = RequestMethod.POST)
	public
	@ResponseBody
	String delete(@RequestBody PackageItem packaging) throws IOException {

		repository.delete(packaging);
		return "OK";
	}

	@RequestMapping(value = "/getPackagesInGroup", method = RequestMethod.POST)
	public
	@ResponseBody
	List<PackageItem> filter(@RequestBody Long group, @RequestBody User owner) throws IOException {
		//TODO get from request
		return Lists.newArrayList(repository.findAll(packageItem.groupId.eq(group).and(packageItem.owner.eq(owner))));
	}

	@RequestMapping(value = "/allPackages", method = RequestMethod.POST)
	public
	@ResponseBody
	List<PackageItem> filter(@RequestBody User owner) throws IOException {

		return Lists.newArrayList(repository.findAll(packageItem.owner.eq(owner)));
	}


}
