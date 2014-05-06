package hcv.spring.config;

import hcv.manager.DropboxFileManager;
import hcv.manager.FileManager;
import hcv.manager.IFileManager;
import hcv.utils.ProductionProfile;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Tomo.
 */
@Configuration
@ComponentScan("hcv")
@EnableWebMvc
@PropertySource("classpath:cloudbees/application.properties")
@Import(DatabaseConfig.class)
@ProductionProfile
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
        return new DropboxFileManager();
    }
}
