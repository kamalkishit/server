package com.humanize.server.content.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.service.AmazonS3Service;
import com.humanize.server.util.ExcelToJson;


@Service
public class ContentService {
	
	@Autowired
	private ContentRepositoryService repositoryService;
	
	@Autowired
	private HtmlScraperService htmlScraper;
	
	@Autowired
	private ImageDownloaderService imageDownloader;
	
	@Autowired
	private AmazonS3Service amazonS3Service;

	public Content create(Content content) {
		content = htmlScraper.scrapHtml(content);
		imageDownloader.downloadImage(content);
		amazonS3Service.putImage(content);
		return repositoryService.create(content);
	}
	
	public Content update(Content content) {
		return repositoryService.update(content);
	}
	
	public Contents findByCategories(List<String> categories, Long createdDate, boolean refresh) {
		if (createdDate == null) {
			return repositoryService.findByCategories(categories);
		} else if (refresh) {
			return repositoryService.findNewByCategories(categories, createdDate);
		} else {
			return repositoryService.findMoreByCategories(categories, createdDate);
		}
	}
	
	public Contents findByIds(List<String> ids) {
		return repositoryService.findByIds(ids);
	}
	
	public void populate() {
		ExcelToJson excelToJson = new ExcelToJson(Config.EXCEL_FILE_PATH);
		List<Content> contents = excelToJson.toJson();
		contents = htmlScraper.scrapHtml(contents);
		repositoryService.create(contents);
	}

	/*
	public ContentService() {
	}

	public void populateContent() {
		ExcelToJson excelToJson = new ExcelToJson(Config.EXCEL_FILE_PATH);
		List<Content> contents = excelToJson.toJson();
		contents = htmlScrapper.scrapHtml(contents);
		repository.save(contents);
	}

	public Content createContent(Content content) {
		content = htmlScrapper.scrapHtml(content);
		imageDownloader.downloadImage(content);

		return repository.save(content);
	}

	public boolean updateContents(Contents contents) {
		List<String> ids = contentHelper.getIds(contents);

		List<Content> resultContents = repository.findAll(ids);
		if (resultContents != null) {
			System.out.println("size:" + resultContents.size());
			System.out.println(resultContents.get(0).getTitle());
			System.out.println(resultContents.get(0).getDescription());
		}
		
		return true;
	}
	
	public List<Content> findByCategory(String category) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		return repository.findAllByCategory(category, pageRequest);
	}
	
	public List<Content> findByCategoryCreatedDateLessThan(String category, long createdDate) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		return repository.findAllByCategoryCreatedDateLessThan(category, createdDate, pageRequest);
	}
	
	public List<Content> findByCategories(ArrayList<String> categories) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		return repository.findAllByCategories(categories, pageRequest);
	}

	public ArrayList<Content> getContent() {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		Page<Content> contents = repository.findAll(pageRequest);

		if (contents != null) {
			return new ArrayList<Content>(contents.getContent());
		}

		return null;
	}
	
	public ArrayList<Content> getContents(List<String> categories) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(new Order(
				Direction.DESC, "createdDate")));
		List<Content> contents = repository.findAllByCategories(categories, pageRequest);

		if (contents != null) {
			return new ArrayList<Content>(contents);
		}

		return null;
	}

	public List<Content> getMoreContent(long startDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		List<Content> contents = repository.findByCreatedDateLessThan(
				startDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	public List<Content> getMoreContents(List<String> categories, long startDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		List<Content> contents = repository.findAllByCategoriesCreatedDateLessThan(categories, 
				startDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}

	public List<Content> getNewContent(long endDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		List<Content> contents = repository.findByCreatedDateGreaterThan(
				endDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	public List<Content> getNewContents(List<String> categories, long endDate) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		List<Content> contents = repository.findAllByCategoriesCreatedDateGreaterThan(categories, 
				endDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	private Pageable createPagination(Direction direction, String field) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(direction,
				field));
		
		return pageRequest;
	}*/
}
