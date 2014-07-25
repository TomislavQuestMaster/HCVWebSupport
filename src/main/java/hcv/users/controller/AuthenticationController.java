package hcv.users.controller;

import com.codahale.metrics.Counter;
import hcv.users.persistance.UserRepository;
import hcv.core.metrics.*;
import hcv.model.Response;
import hcv.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static hcv.model.user.QUser.*;

/**
 * @author tdubravcevic
 */
@Controller
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginCounters loginCounters;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Response registering(@RequestBody User request) {

		User found = userRepository.findOne(user.username.eq(request.getUsername()));

		if (found != null) {
			return failed("User exists", 1);
		}

		userRepository.save(request);

		loginCounters.getCounterMap().put(request.getUsername(), new Counter());

		return succeeded("Created user");
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public @ResponseBody Response verifying(@RequestBody User request) {

		User found = userRepository.findOne(user.username.eq(request.getUsername()));

		if (found == null) {
			return failed("Verification failed, not found", 2);
		}

		if (!request.getPassword().equals(found.getPassword())) {
			return failed("Verification failed, wrong password", 3);
		}

		incrementLoginCounter(found);

		return succeeded("Verification succeeded");
	}

	private void incrementLoginCounter(User user) {

		Counter counter = loginCounters.getCounterMap().get(user.getUsername());
		if (counter == null) {
			counter = new Counter();
			loginCounters.getCounterMap().put(user.getUsername(), counter);
		}
		counter.inc();
	}

	private Response failed(String message, Integer status) {

		Response response = new Response();
		response.setStatus(status);
		response.setMessage(message);
		return response;
	}

	private Response succeeded(String message) {

		Response response = new Response();
		response.setStatus(0);
		response.setMessage(message);
		return response;
	}

}
