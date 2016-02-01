package com.humanize.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.humanize.server.service.ContentServiceImpl;

@SpringBootApplication
public class HumanizeServerApp extends SpringBootServletInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(HumanizeServerApp.class);
	private static final String TAG = HumanizeServerApp.class.getSimpleName();

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(HumanizeServerApp.class);
	}

	public static void main(String[] args) {
		try {
			HumanizeServerApp.run(args);
		} catch (Exception exception) {
			logger.error(TAG, exception);
		}

		SpringApplication.run(HumanizeServerApp.class, args);
	}

	public static void run(String... args) throws Exception {

	}
}
