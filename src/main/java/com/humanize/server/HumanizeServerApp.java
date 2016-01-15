package com.humanize.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class HumanizeServerApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(HumanizeServerApp.class);
	}

	public static void main(String[] args) {
		try {
			HumanizeServerApp.run(args);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		SpringApplication.run(HumanizeServerApp.class, args);
	}

	public static void run(String... args) throws Exception {

	}
}
