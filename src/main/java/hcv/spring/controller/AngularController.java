package hcv.spring.controller;

import hcv.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tdubravcevic
 */
@Controller
public class AngularController extends CorsCommon{


	@RequestMapping(value = "/hook", method = RequestMethod.POST)
	public void test(@RequestBody Response response) {
		System.out.println(response.getMessage());
	}
}
