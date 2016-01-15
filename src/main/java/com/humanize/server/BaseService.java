package com.humanize.server;

import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.exception.EmailSendingException;

public interface BaseService {
	
	boolean invite(InviteFriend inviteFriend) throws EmailSendingException;
	boolean suggest(SuggestArticle suggestArticle) throws EmailSendingException;
	boolean contactUs(ContactUs contactUs) throws EmailSendingException;
	Contents findContent(ContentSearchParams contentSearchParams) throws ContentNotFoundException;
}
