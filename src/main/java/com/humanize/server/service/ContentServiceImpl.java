package com.humanize.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.service.EmailService;
import com.humanize.server.authentication.service.UserRepositoryService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.common.JsonWebTokenServiceImpl;
import com.humanize.server.config.Config;
import com.humanize.server.content.dao.ContentRepository;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdateException;
import com.humanize.server.content.service.ContentRepositoryService;
import com.humanize.server.content.service.HtmlScraperService;
import com.humanize.server.content.service.ImageDownloaderService;
import com.humanize.server.data.User;
import com.humanize.server.exception.TokenValidationException;
import com.humanize.server.util.ExcelToJson;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentRepositoryService repositoryService;
	
	@Autowired
	private ContentRepository repository;
	
	@Autowired
	private HtmlScraperService htmlScraperService;
	
	@Autowired
	private ImageDownloaderService imageDownloaderService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AmazonS3Service amazonS3Service;
	
	@Autowired
	private UserRepositoryService userRepositoryService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Content create(String token, Content content) throws ContentCreationException {
		try {
			User user = getUser(token);
			content = htmlScraperService.scrapHtml(content);
			content.setUserId(user.getEmailId());
			content.setType("Positive");
			imageDownloaderService.downloadImage(content);
			//amazonS3Service.putImage(content);
			return repositoryService.create(content);
		} catch (ContentCreationException exception) {
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ContentCreationException(ExceptionConfig.CONTENT_CREATION_ERROR_CODE, ExceptionConfig.CONTENT_CREATION_EXCEPTION);
		}
	}
	
	public void createInBulk(String token, Content content) {
		try {
			User user = getUser(token);
			content = htmlScraperService.scrapHtml(content);
			content.setUserId(user.getEmailId());
			content.setType("Positive");
			imageDownloaderService.downloadImage(content);
			//amazonS3Service.putImage(content);
			repositoryService.create(content);
		} catch (Exception exception) {
			
		}
	}
	
	public void upload(String token) throws Exception {
		try {
			getUser(token);
			ExcelToJson excelToJson = new ExcelToJson(Config.EXCEL_FILE_PATH);
			List<Content> contents = excelToJson.toJson();
				
			for (Content content: contents) {
				createInBulk(token, content);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public Content update(String token, Content content) throws ContentUpdateException {
		try {
			getUser(token);
			return repositoryService.update(content);
		} catch (Exception exception) {
			throw new ContentUpdateException(0, null);
		}
	}
	
	public Contents findByCategories(String token, List<String> categories, Long createdDate, boolean refresh) throws ContentNotFoundException {
		User user = null;
		
		if (!token.isEmpty() && categories != null) {
			try {
				user = getUser(token);
				if (createdDate == null) {
					return repositoryService.findByCategories(categories);
				} else if (refresh) {
					return repositoryService.findNewByCategories(categories, createdDate);
				} else {
					return repositoryService.findMoreByCategories(categories, createdDate);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				throw new ContentNotFoundException(0, null);
			}
		} else if (!token.isEmpty() && categories == null){
			try {
				user = getUser(token);
				if (createdDate == null) {
					return repositoryService.findByCategories(user.getCategories());
				} else if (refresh) {
					return repositoryService.findNewByCategories(user.getCategories(), createdDate);
				} else {
					return repositoryService.findMoreByCategories(user.getCategories(), createdDate);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				throw new ContentNotFoundException(0, null);
			}
		} else if (token.isEmpty() && categories != null){
			if (createdDate == null) {
				return repositoryService.findByCategories(categories);
			} else if (refresh) {
				return repositoryService.findNewByCategories(categories, createdDate);
			} else {
				return repositoryService.findMoreByCategories(categories, createdDate);
			}
		} else {
			if (createdDate == null) {
				return repositoryService.findByCategories(getCategories());
			} else if (refresh) {
				return repositoryService.findNewByCategories(getCategories(), createdDate);
			} else {
				return repositoryService.findMoreByCategories(getCategories(), createdDate);
			}
		}
	}
	
	public Contents findByIds(String token, List<String> ids) throws ContentNotFoundException {
		try {
			getUser(token);
			return repositoryService.findByIds(ids);
		} catch (Exception exception) {
			throw new ContentNotFoundException(0, null);
		}
	}
	
	public Contents findBookmarks(String token, List<String> bookmarkIds) throws ContentNotFoundException {
		try {
			getUser(token);
			return repositoryService.findByIds(bookmarkIds);
		} catch (Exception exception) {
			throw new ContentNotFoundException(0, null);
		}
	}
	
	public Contents findRecommendations(String token, List<String> recommendations) throws ContentNotFoundException {
		try {
			getUser(token);
			return repositoryService.findByIds(recommendations);
		} catch (Exception exception) {
			throw new ContentNotFoundException(0, null);
		}		
	}
	
	public boolean recommendArticle(String token, String contentUrl) throws Exception {
		try {
			getUser(token);
			emailService.sendEmail(new Message("kamal@humannize.com", "Suggested Article", contentUrl));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}
	
	public boolean updateRecommendationCount(String token, String contentId, boolean flag) throws ContentUpdateException {
		try {
			getUser(token);
			Content content = repositoryService.findOne(contentId);
			
			if (flag) {
				content.setRecommendedCount(content.getRecommendedCount() + 1);
			} else {
				content.setRecommendedCount(content.getRecommendedCount() - 1);
			}
			
			content.setViewedCount(content.getViewedCount() + 1);
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(0, null);
		} catch (Exception exception) {
			throw new ContentUpdateException(0, null);
		}
	}
	
	public boolean incrViewedCount(String token, String contentId) throws ContentUpdateException {
		try {
			getUser(token);
			Content content = repositoryService.findOne(contentId);
			content.setViewedCount(content.getViewedCount() + 1);
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(0, null);
		} catch (Exception exception) {
			throw new ContentUpdateException(0, null);
		}
	}
	
	public boolean incrSharedCount(String token, String contentId) throws ContentUpdateException {
		try {
			getUser(token);
			Content content = repositoryService.findOne(contentId);
			content.setSharedCount(content.getSharedCount() + 1);
			content.setViewedCount(content.getViewedCount() + 1);
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(0, null);
		} catch (Exception exception) {
			throw new ContentUpdateException(0, null);
		}
	}
	
	private User getUser(String token) throws UserNotFoundException {
		try {
			String emailId = JsonWebTokenServiceImpl.getInstance().validateToken(token);
			return userRepositoryService.findByEmailId(emailId);
		} catch (TokenValidationException exception) {
			throw new UserNotFoundException(0, null);
		}	
	}
	
	private List<String> getCategories() {
		List<String> categories = new ArrayList<>();
		categories.add("Achievers");
		categories.add("Beautiful");
		categories.add("Education");
		categories.add("Environment");
		categories.add("Empowerment");
		categories.add("Governance");
		categories.add("Health");
		categories.add("Humanity");
		categories.add("Law and Justice");
		categories.add("Real Heroes");
		categories.add("Science and Tech");
		categories.add("Sports");
		
		return categories;
	}

/*
	
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
