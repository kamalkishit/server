package com.humanize.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.exception.EmailSendingException;

@RestController
public class BaseController {
	
	@Autowired
	BaseService baseService;
	
	@RequestMapping("/invite")
	public ResponseEntity<Boolean> invite(@RequestBody InviteFriend inviteFriend) throws EmailSendingException {
		return new ResponseEntity<Boolean>(baseService.invite(inviteFriend), HttpStatus.OK);
	}
	
	@RequestMapping("/suggest")
	public ResponseEntity<Boolean> suggest(@RequestBody SuggestArticle suggestArticle) throws EmailSendingException {
		return new ResponseEntity<Boolean>(baseService.suggest(suggestArticle), HttpStatus.OK);
	}
	
	@RequestMapping("/contactUs")
	public ResponseEntity<Boolean> contactUs(@RequestBody ContactUs contactUs) throws EmailSendingException {
		return new ResponseEntity<Boolean>(baseService.contactUs(contactUs), HttpStatus.OK);
	}
	
	@RequestMapping("/content")
	public ResponseEntity<Contents> find(@RequestBody ContentSearchParams contentSearchParams) throws ContentNotFoundException {
		return new ResponseEntity<Contents>(baseService.findContent(contentSearchParams), HttpStatus.OK);
	}
}
