package com.humanize.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.humanize.server.dao.ContentRepository;
import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;
import com.humanize.server.helper.ContentHelper;
import com.humanize.server.util.ExcelToJson;
import com.humanize.server.util.HtmlParserService;

@Service
public class ContentService {

	@Autowired
	private ContentRepository repository;

	@Autowired
	private ContentHelper contentHelper;
 
	@Autowired
	private HtmlParserService htmlParser;

	public ContentService() {
	}

	public void populateContent() {
		ExcelToJson excelToJson = new ExcelToJson(
				"/root/TLI.xlsx");
		ArrayList<Content> contents = excelToJson.toJson();
		HtmlParserService htmlParserService = new HtmlParserService();
		contents = htmlParserService.parse(contents);
		repository.save(contents);
	}

	public Content createContent(Content content) {
		content = htmlParser.parse(content);

		if (content != null) {
			repository.save(content);
		}

		return content;
	}

	public boolean updateContents(Contents contents) {
		ArrayList<String> ids = contentHelper.getIds(contents);

		ArrayList<Content> resultContents = repository.findAll(ids);
		if (resultContents != null) {
			System.out.println("size:" + resultContents.size());
			System.out.println(resultContents.get(0).getTitle());
			System.out.println(resultContents.get(0).getDescription());
		}
		return true;
	}

	public ArrayList<Content> getBookmarks(List<String> ids) {
		ArrayList<Content> bookmarks = repository.findAll(ids);

		return bookmarks;
	}

	public ArrayList<Content> getLikes(List<String> ids) {
		return repository.findAll(ids);
	}

	
	public ArrayList<Content> findByCategory(String category) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		return repository.findAllByCategory(category, pageRequest);
	}
	
	public ArrayList<Content> findByCategoryCreatedDateLessThan(String category, long createdDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		return repository.findAllByCategoryCreatedDateLessThan(category, createdDate, pageRequest);
	}
	
	public ArrayList<Content> findByCategories(ArrayList<String> categories) {

		PageRequest pageRequest = new PageRequest(0, 20, new Sort(new Order(
				Direction.DESC, "createdDate")));
		
		//return repository.findAllByCategories(categories, pageRequest);
		return null;
	}

	public ArrayList<Content> getPaper(List<String> ids) {
		return repository.findAll(ids);
	}

	public ArrayList<Content> getContent() {
		PageRequest pageRequest = new PageRequest(0, 20, new Sort(new Order(
				Direction.DESC, "createdDate")));
		Page<Content> contents = repository.findAll(pageRequest);

		if (contents != null) {
			return new ArrayList<Content>(contents.getContent());
		}

		return null;
	}
	
	public ArrayList<Content> getContents(List<String> categories) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(new Order(
				Direction.DESC, "createdDate")));
		Page<Content> contents = repository.findAllByCategories(categories, pageRequest);

		if (contents != null) {
			return new ArrayList<Content>(contents.getContent());
		}

		return null;
	}

	public ArrayList<Content> getMoreContent(long startDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		ArrayList<Content> contents = repository.findByCreatedDateLessThan(
				startDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	public ArrayList<Content> getMoreContents(List<String> categories, long startDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		ArrayList<Content> contents = repository.findAllByCategoriesCreatedDateLessThan(categories, 
				startDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}

	public ArrayList<Content> getNewContent(long endDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		ArrayList<Content> contents = repository.findByCreatedDateGreaterThan(
				endDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	public ArrayList<Content> getNewContents(List<String> categories, long endDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		ArrayList<Content> contents = repository.findAllByCategoriesCreatedDateGreaterThan(categories, 
				endDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
}
