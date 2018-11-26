package by.bsuir.advertismentplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"by.bsuir.advertismentplatform.repository",
        "by.bsuir.advertismentplatform.service"})
public class AdverttismentPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdverttismentPlatformApplication.class, args);
    }
}
