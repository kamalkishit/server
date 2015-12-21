package com.humanize.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HumanizeServerApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(HumanizeServerApp.class);
	}

	public static void main(String[] args) {
		try {
			HumanizeServerApp.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SpringApplication.run(HumanizeServerApp.class, args);
	}

	public static void run(String... args) throws Exception {

	}
}
