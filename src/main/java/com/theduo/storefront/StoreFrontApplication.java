package com.theduo.storefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class StoreFrontApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreFrontApplication.class, args);
		Environment env = context.getEnvironment();

		String port = env.getProperty("server.port", "8080");
		System.out.println("Server running on port http://localhost:" + port);

    }

}
