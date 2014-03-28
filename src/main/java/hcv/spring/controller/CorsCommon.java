package hcv.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsCommon {

	@RequestMapping(method = RequestMethod.OPTIONS)
	public void commonOptions(HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, my-cool-header");
		response.addHeader("Access-Control-Max-Age", "60"); // seconds to cache preflight request --> less OPTIONS traffic
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.addHeader("Access-Control-Allow-Origin", "*");
	}

}
