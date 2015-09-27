package com.humanize.server.dao;

import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.data.Content;

public interface ContentRepository extends MongoRepository<Content, String> {

	public ArrayList<Content> findAllOrderByCreatedDate(PageRequest pageRequest);

	public ArrayList<Content> findAllOrderByCreatedDate();

	public ArrayList<Content> findByOrderByCreatedDateDesc();

	public ArrayList<Content> findByCreatedDateLessThan(long createdDate,
			Pageable pageRequest);

	public ArrayList<Content> findByCreatedDateGreaterThan(long createdDate,
			Pageable pageRequest);

	public ArrayList<Content> findByContentIdIn(ArrayList<String> contentIds);

	public ArrayList<Content> findAll(Iterable<String> ids);
}
