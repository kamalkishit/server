package com.humanize.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.dbserver.data.VerificationCode;
import com.humanize.dbserver.exception.VerificationCodeCreationException;
import com.humanize.dbserver.exception.VerificationCodeDeletionException;
import com.humanize.dbserver.exception.VerificationCodeNotFoundException;
import com.humanize.dbserver.exception.VerificationCodeUpdationException;
import com.humanize.dbserver.service.VerificationCodeService;

@RestController
public class VerificationCodeController {
	
	@Autowired
	VerificationCodeService verificationCodeService;
	
	@RequestMapping("/verificationCode/create")
	public ResponseEntity<VerificationCode> create(@RequestBody VerificationCode verificationCode) 
			throws VerificationCodeCreationException {
		return new ResponseEntity<VerificationCode>(verificationCodeService.create(verificationCode), HttpStatus.OK);
	}
	
	@RequestMapping("/verificationCode/update")
	public ResponseEntity<VerificationCode> update(@RequestBody VerificationCode verificationCode) 
			throws VerificationCodeUpdationException {
		return new ResponseEntity<VerificationCode>(verificationCodeService.update(verificationCode), HttpStatus.OK);
	}
	
	@RequestMapping("/verificationCode/find")
	public ResponseEntity<VerificationCode> findByEmailId(@RequestParam("emailId") String emailId) 
			throws VerificationCodeNotFoundException {
		return new ResponseEntity<VerificationCode>(verificationCodeService.findByEmailId(emailId), HttpStatus.OK);
	}
	
	@RequestMapping("/verificationCode/delete")
	public ResponseEntity<Boolean> delete(@RequestBody VerificationCode verificationCode) 
			throws VerificationCodeDeletionException {
		return new ResponseEntity<Boolean>(verificationCodeService.delete(verificationCode), HttpStatus.OK);
	}
}
