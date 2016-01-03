package com.humanize.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost");
	}

	@Override
	protected String getDatabaseName() {
		return "humanize";
	}
}
