package com.humanize.server.config;

public class Config {

	public static final String DATABASE = "humanize";
	public static final String DB_SERVER = "localhost";
	
	public static final String GOOGLE_URL_SHORNER_API_URL = "https://www.googleapis.com/urlshortener/v1/url";
	public static final String GOOLE_URL_SHORTNER_API_KEY = "AIzaSyCQiHgcWArX0lfhGSxqwGepDpc1W9eJEIc";

	public static final String IMAGE_FOLDER = "/root/images/";
	public static final String TEMP_IMAGE_FOLDER = "/root/temp/";
	
	public static final String S3_BUCKET_IMAGES = "humanize-images-prod";
	
	public static final String URL_SERVER = "http://www.humannize.com";
	public static final String URL_IMAGES = URL_SERVER + "/images/";
	public static final String URL_CONTENT = "http://humannize.com/content/";
	
	public static final String URL_PASSWORD_RESET = URL_SERVER + "/reset";
	
	public static final String EXCEPTIONS = "exceptions";
	
	public static final String POSITIVE = "Positive";
	public static final String INFO = "Info";
	public static final String NEGATIVE = "Negative";
	
	public static final String HELLO_EMAIL_ID = "hello@humannize.com";
	public static final String CONTACT_EMAIL_ID = "contact@humannize.com";
	public static final String INVITATION_SUBJECT = "Invited to HUMANIZE";
	public static final String ADMIN_EMAIL_ID = "kamal@humannize.com";
	public static final String SUBMIT_ARTICLE_SUBJECT = "Submitted article";
	public static final String CONTACT_US_SUBJECT = "Feedback";
	
	public static final String EXCEL_FILE_PATH = "/root/December.xlsx";
	
	public static final String USER_AGENT = "User-Agent";
	public static final String MOZILLA = "Mozilla";
	
	public static final int IMAGE_SIZE = 512;
}