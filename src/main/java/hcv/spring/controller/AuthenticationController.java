package hcv.spring.controller;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import hcv.data.service.UserService;
import hcv.metrics.HcvInstrumentedFilterContextListener;
import hcv.metrics.IMetricsProvider;
import hcv.metrics.LoginCounters;
import hcv.model.Response;
import hcv.model.user.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tdubravcevic
 */
@Controller
public class AuthenticationController{

	@Autowired
	private UserService service;

	@Autowired
	private LoginCounters loginCounters;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public
	@ResponseBody
	Response registering(
			@RequestBody
			User request) {

		User found = service.findByUsername(request.getUsername());

		if (found != null) {
			return failed("User exists", 1);
		}

		service.create(request);

		loginCounters.getCounterMap().put(request.getUsername(), new Counter());

		return succeeded("Created user");
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public
	@ResponseBody
	Response verifying(
			@RequestBody
			User request) {

		User found = service.findByUsername(request.getUsername());

		if (found == null) {
			return failed("Verification failed, not found", 2);
		}

		if (!request.getPassword().equals(found.getPassword())) {
			return failed("Verification failed, wrong password", 3);
		}

		loginCounters.getCounterMap().get(found.getUsername()).inc();

		return succeeded("Verification succeeded");
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
