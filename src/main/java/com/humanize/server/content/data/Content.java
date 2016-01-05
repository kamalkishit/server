package com.humanize.server.content.data;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Content {

	@Id
	private String id;

	// business id
	@Indexed(unique = true)
	private long contentId;

	@Indexed(unique = true)
	@NotEmpty
	private String url;

	@NotEmpty
	private String userId;

	@NotEmpty
	private String title;

	@NotEmpty
	private String description;

	@NotEmpty
	private String imageURL;

	private String originalImageURL;

	private String source;

	// positive, info, negative
	private String type;

	@NotEmpty
	private String category;

	private String videoURL;

	private int recommendedCount;

	private int viewedCount;

	private int sharedCount;
	
	private boolean isVerified;
	
	private boolean isDeleted;

	@CreatedDate
	private long createdDate;

	@LastModifiedDate
	private long lastModifiedDate;
	
	public Content() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getOriginalImageURL() {
		return originalImageURL;
	}

	public void setOriginalImageURL(String originalImageURL) {
		this.originalImageURL = originalImageURL;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public int getRecommendedCount() {
		return recommendedCount;
	}

	public void setRecommendedCount(int recommendedCount) {
		this.recommendedCount = recommendedCount;
	}

	public int getViewedCount() {
		return viewedCount;
	}

	public void setViewedCount(int viewedCount) {
		this.viewedCount = viewedCount;
	}

	public int getSharedCount() {
		return sharedCount;
	}

	public void setSharedCount(int sharedCount) {
		this.sharedCount = sharedCount;
	}
	
	public boolean isVerified() {
		return isVerified;
	}

	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
}
