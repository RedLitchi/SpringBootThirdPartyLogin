package com.nieqiang.webapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class QQControllerApplication {

	public static void main(String[] nieqiang) {
		SpringApplication.run(QQControllerApplication.class, nieqiang);
	}
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}