package com.humanize.server.content.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.humanize.server.content.data.Content;

public interface ContentRepository extends MongoRepository<Content, String> {

	public ArrayList<Content> findAllOrderByCreatedDate(PageRequest pageRequest);

	public ArrayList<Content> findAllOrderByCreatedDate();

	public ArrayList<Content> findByOrderByCreatedDateDesc();

	public ArrayList<Content> findByCreatedDateLessThan(long createdDate,
			Pageable pageRequest);

	public ArrayList<Content> findByCreatedDateGreaterThan(long createdDate,
			Pageable pageRequest);

	public ArrayList<Content> findByContentIdIn(ArrayList<String> contentIds);
	
	public Page<Content> findAll(Pageable pageRequest);

	public ArrayList<Content> findAll(Iterable<String> ids);
	
	public Content findByEmailId(String emailId);
	
	@Query("{ 'category': ?0 }")
	public ArrayList<Content> findAllByCategory(String category, Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }, 'createdDate': { $lt : ?1 }}")
	public ArrayList<Content> findAllByCategoriesCreatedDateLessThan(List<String> categories, long createdDate,
			Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }, 'createdDate': { $gt : ?1 }}")
	public ArrayList<Content> findAllByCategoriesCreatedDateGreaterThan(List<String> categories, long createdDate,
			Pageable pageRequest);
	
	@Query("{ 'category': ?0, 'createdDate': { $gt : ?1 }}")
	public ArrayList<Content> findAllByCategoryCreatedDateLessThan(String category, long createdDate, Pageable pageRequest);
	
	@Query("{ 'category': { $in: ?0 }}")
	public Page<Content> findAllByCategories(List<String> categories, Pageable pageRequest);
}
