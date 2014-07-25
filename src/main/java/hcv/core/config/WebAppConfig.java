package hcv.core.config;

import hcv.core.manager.*;
import hcv.core.metrics.*;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import hcv.users.persistance.UserService;
import hcv.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomo.
 */
@Configuration
@ComponentScan("hcv")
@EnableWebMvc
@PropertySource("classpath:localhost/application.properties")
@Import(DatabaseConfig.class)
public class WebAppConfig {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private UserService service;

	@Bean
	public UrlBasedViewResolver setupViewResolver() {

		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/app/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public IFileManager fileManager() {
		//return new DropboxFileManager();
		return new FileManager();
	}

	@Bean
	public LoginCounters loginCounters() {

		MetricRegistry metricRegistry = (MetricRegistry) servletContext.getAttribute("com.codahale.metrics.servlets.MetricsServlet" +
																							 ".registry");

		Map<String, Counter> counters = new HashMap<String, Counter>();

		for (User user : service.findAll()) {

			String username = user.getUsername();

			Counter counter = metricRegistry.counter(username);

			if (counter != null) {
				counters.put(username, counter);
				continue;
			}

			counter = new Counter();
			metricRegistry.register(username, counter);
			counters.put(username, counter);
		}

		return new LoginCounters(counters);
	}
}
