package hcv.spring.config;

import hcv.manager.DropboxFileManager;
import hcv.manager.FileManager;
import hcv.manager.IFileManager;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by Tomo.
 */
@Configuration
@ComponentScan("hcv")
@EnableWebMvc
@PropertySource("classpath:hcv/application.properties")
@Import(DatabaseConfig.class)
public class WebAppConfig {

	@Bean
	public UrlBasedViewResolver setupViewResolver() {

		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/app/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

    @Bean
    public IFileManager fileManager(){
		//return new DropboxFileManager();
        return new FileManager();
    }
}
