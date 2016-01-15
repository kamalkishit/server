package com.humanize.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.service.EmailService;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.service.ContentRepositoryService;
import com.humanize.server.exception.EmailSendingException;

@Service
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	EmailHelper emailHelper;
	
	@Autowired
	private ContentRepositoryService repositoryService;

	public boolean invite(InviteFriend inviteFriend) throws EmailSendingException {
		try {
			return emailService.sendEmail(emailHelper.createInviteFriendMail(inviteFriend));
		} catch (Exception exception) {
			
		}
		return false;
	}
	
	public boolean suggest(SuggestArticle suggestArticle) throws EmailSendingException {
		try {
			return emailService.sendEmail(emailHelper.createSuggestArticleMail(suggestArticle));
		} catch (Exception exception) {
			
		}
		
		return false;
	}
	
	public boolean contactUs(ContactUs contactUs) throws EmailSendingException {
		try {
			return emailService.sendEmail(emailHelper.createContactUsMail(contactUs));
		} catch (Exception exception) {
			
		}
		return false;
	}
	
	public Contents findContent(ContentSearchParams contentSearchParams) throws ContentNotFoundException {
		if (contentSearchParams.getCreatedDate() != 0 && contentSearchParams.getRefresh()) {
			return repositoryService.findNewByCategories(contentSearchParams.getCategories(), contentSearchParams.getCreatedDate());
		} else if (contentSearchParams.getCreatedDate() != 0) {
			return repositoryService.findMoreByCategories(contentSearchParams.getCategories(), contentSearchParams.getCreatedDate());
		} else {
			return repositoryService.findByCategories(contentSearchParams.getCategories());
		}
	}
}
