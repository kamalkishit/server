package com.humanize.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.humanize.server.data.Content;

public interface ContentRepository extends MongoRepository<Content, String> {
	
	@Query("{ 'category': ?0 }")
	List<Content> findByCategory(String category, Pageable pageRequest);
	
	@Query("{ 'category': ?0, 'createdDate': { $gt : ?1 }}")
	List<Content> findNewByCategory(String category, long createdDate,
			Pageable pageRequest);
	
	@Query("{ 'category': ?0, 'createdDate': { $lt : ?1 }}")
	List<Content> findMoreByCategory(String category, long createdDate,
			Pageable pageRequest);

	@Query("{ 'category': { $in: ?0 }}")
	List<Content> findByCategories(List<String> categories, Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }, 'createdDate': { $gt : ?1 }}")
	List<Content> findNewByCategories(List<String> categories, long createdDate,
			Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }, 'createdDate': { $lt : ?1 }}")
	List<Content> findMoreByCategories(List<String> categories, long createdDate,
			Pageable pageRequest);
	
	List<Content> findAll(Iterable<String> ids);
	
	List<Content> findAll();
	
	Content findByUrlId(String urlId);
}
