package jane.rentcarproject_gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
public class RentCarProjectGradleApplication {

	public static void main(String[] args) {
		var applicationContext = SpringApplication.run(RentCarProjectGradleApplication.class, args);
	}
}
