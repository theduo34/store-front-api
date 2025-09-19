package com.theduo.storefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StoreFrontApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreFrontApplication.class, args);
		Environment env = context.getEnvironment();

		String port = env.getProperty("server.port", "8080");
		System.out.println("Server running on port http://localhost:" + port);

    }

}
