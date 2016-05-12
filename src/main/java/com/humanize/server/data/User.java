package com.humanize.server.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.humanize.server.config.Config;
import com.humanize.server.config.StringConstants;

@Document
public class User {

	@Id
	private String id;

	@Indexed(unique = true)
	private long userId;

	@Indexed(unique = true)
	private String emailId;

	@NotEmpty
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String originalCity;
	
	private String currentCity;
	
    private PaperTime paperTime;
    
    private boolean notification;
	
	private List<String> categories;

	private List<String> upvotes;

	private List<String> bookmarks;

	private List<String> posts;
	
	private boolean isVerified;
	
	private boolean isDeleted;
	
	@CreatedDate
	private long createdDate;

	@LastModifiedDate
	private long lastModifiedDate;
	
	@NotNull
	private boolean accountExpired;

	@NotNull
	private boolean accountLocked;

	@NotNull
	private boolean credentialsExpired;

	@NotNull
	private boolean accountEnabled;
	
    public User() {
        categories = new ArrayList<>();
        bookmarks = new ArrayList<>();
        upvotes = new ArrayList<>();
        paperTime = new PaperTime(Config.PAPER_HOUR, Config.PAPER_MINUTE);
        notification = true;

        categories.add(StringConstants.ACHIEVERS);
        categories.add(StringConstants.BEAUTIFUL);
        categories.add(StringConstants.CHANGEMAKERS);
        categories.add(StringConstants.EDUCATION);
        categories.add(StringConstants.EMPOWERMENT);
        categories.add(StringConstants.ENVIRONMENT);
        categories.add(StringConstants.GOVERNANCE);
        categories.add(StringConstants.HEALTH);
        categories.add(StringConstants.HUMANITY);
        categories.add(StringConstants.INSPIRING);
        categories.add(StringConstants.KINDNESS);
        categories.add(StringConstants.LAW_AND_JUSTICE);
        categories.add(StringConstants.REAL_HEROES);
        categories.add(StringConstants.SCIENCE_AND_TECH);
        categories.add(StringConstants.SMILE);
        categories.add(StringConstants.SPORTS);
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(List<String> upvotes) {
		this.upvotes = upvotes;
	}

	public List<String> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<String> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<String> getPosts() {
		return posts;
	}

	public void setPosts(List<String> posts) {
		this.posts = posts;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
