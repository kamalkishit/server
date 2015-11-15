package com.humanize.server.content.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.humanize.server.content.data.Content;

public interface ContentRepository extends MongoRepository<Content, String> {

	public List<Content> findAllOrderByCreatedDate(PageRequest pageRequest);

	public List<Content> findAllOrderByCreatedDate();

	public List<Content> findByOrderByCreatedDateDesc();

	public List<Content> findByCreatedDateLessThan(long createdDate,
			Pageable pageRequest);

	public List<Content> findByCreatedDateGreaterThan(long createdDate,
			Pageable pageRequest);

	public List<Content> findByContentIdIn(List<String> contentIds);
	
	public Page<Content> findAll(Pageable pageRequest);

	public List<Content> findAll(Iterable<String> ids);
	
	@Query("{ 'category': ?0 }")
	public List<Content> findAllByCategory(String category, Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }, 'createdDate': { $lt : ?1 }}")
	public List<Content> findAllByCategoriesCreatedDateLessThan(List<String> categories, long createdDate,
			Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }, 'createdDate': { $gt : ?1 }}")
	public List<Content> findAllByCategoriesCreatedDateGreaterThan(List<String> categories, long createdDate,
			Pageable pageRequest);
	
	@Query("{ 'category': ?0, 'createdDate': { $gt : ?1 }}")
	public List<Content> findAllByCategoryCreatedDateLessThan(String category, long createdDate, Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }}")
	public List<Content> findAllByCategories(List<String> categories, Pageable pageRequest);
}
