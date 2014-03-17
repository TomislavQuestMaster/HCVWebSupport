package hcv.spring.controller;

import hcv.spring.model.Response;
import hcv.spring.model.User;
import hcv.spring.data.service.UserService;
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
	private UserService service;

	@RequestMapping(value="/register", method= RequestMethod.POST)
	public @ResponseBody
	Response registering(@RequestBody
					  User request) {

        User found = service.findByUsername(request.getUsername());

        if(found!=null){
            return failed("User exists");
        }

        service.create(request);

		return succeeded("Created user");
	}

    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    public @ResponseBody
    Response verifying(@RequestBody
                      User request) {

        User found = service.findByUsername(request.getUsername());

        if(found==null || !request.getPassword().equals(found.getPassword())){
            return failed("Verification failed");
        }

        return succeeded("Verification succeeded");
    }

    private Response failed(String message){
        Response response = new Response();
        response.setStatus(1);
        response.setMessage(message);
        return response;
    }

    private Response succeeded(String message){
        Response response = new Response();
        response.setStatus(0);
        response.setMessage(message);
        return response;
    }

}
