package com.humanize.server;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.humanize.server.dao.ContentRepository;
import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;
import com.humanize.server.service.ContentService;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Autowired
	private ContentRepository repository;

	@Autowired
	private ContentService contentService;

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void run(String... args) throws Exception {

		System.out.println("i m here");

		Content content = new Content();
		content.setContentId("e4b371c1-9a7b-4237-84a3-d2f864515fbb");

		ArrayList<Content> contentList = new ArrayList<Content>();
		contentList.add(content);

		Contents contents = new Contents();
		contents.setContents(contentList);
		;

		boolean result = contentService.updateContents(contents);
		if (result) {
			System.out.println("success");
		}
	}
}
