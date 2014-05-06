package hcv.spring.config;

import hcv.manager.DropboxFileManager;
import hcv.manager.IFileManager;
import hcv.utils.TestProfile;
import org.springframework.context.annotation.*;

import java.io.IOException;

/**
 * @author tdubravcevic
 */
@Configuration
@ComponentScan("hcv")
@PropertySource("classpath:localhost/application.properties")
@Import(DatabaseConfig.class)
@TestProfile
public class SystemTestConfiguration {

	@Bean
	public IFileManager fileManager(){
		return new DropboxFileManager();
	}

	public SystemTestConfiguration() throws IOException {
	}
}
