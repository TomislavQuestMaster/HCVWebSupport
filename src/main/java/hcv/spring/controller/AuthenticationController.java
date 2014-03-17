package hcv.spring.controller;

import hcv.spring.model.Data;
import hcv.spring.model.Response;
import hcv.spring.model.UpdateRequest;
import hcv.spring.model.User;
import hcv.spring.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tdubravcevic
 */
@Controller
public class AuthenticationController {

	@Autowired
	private IDataService IDataService;

	@RequestMapping(value="/authenticate", method= RequestMethod.POST)
	public @ResponseBody
	Response updating(@RequestBody
					  User request) {



		Response response = new Response();
		response.setStatus(0);
		response.setMessage("Update successful");

		return response;
	}

}
