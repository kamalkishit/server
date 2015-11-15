package com.humanize.server;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.humanize.server.content.dao.ContentRepository;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.service.ContentService;
import com.humanize.server.util.HtmlParserService;

@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true)
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
		try {
			//EmailSenderService emailSender = new EmailSenderService();
			//emailSender.sendEmail();
			App.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SpringApplication.run(App.class, args);
	}

	public static void run(String... args) throws Exception {

		System.out.println("i m here");

		Content content = new Content();
		content.setContentId("e4b371c1-9a7b-4237-84a3-d2f864515fbb");

		ArrayList<Content> contentList = new ArrayList<Content>();
		contentList.add(content);

		Contents contents = new Contents();
		contents.setContents(contentList);
		;

		HtmlParserService htmlParserService = new HtmlParserService();
		// htmlParserService
		// .parse("http://thelogicalindian.com/news/video-saudi-arabia-to-head-the-u-n-human-rights-council-even-their-own-citizens-will-disagree/");

		/*
		 * boolean result = contentService.updateContents(contents); if (result)
		 * { System.out.println("success"); }
		 */
	}
}
