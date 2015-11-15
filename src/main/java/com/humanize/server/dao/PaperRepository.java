package com.humanize.server.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.content.data.Content;
import com.humanize.server.data.Paper;
import com.humanize.server.data.User;

public interface PaperRepository extends MongoRepository<Paper, String> {
	
	public Paper findByPaperDate(String paperDate);

}
