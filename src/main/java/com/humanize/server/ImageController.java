package com.humanize.server;

import java.io.InputStream;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.service.AmazonS3Service;


@RestController
public class ImageController {
	
	@Autowired
	AmazonS3Service imageService;
	
	@RequestMapping(value = "/images", method = RequestMethod.GET, 
			headers="Accept=image/jpeg, image/jpg, image/png", produces = "image/jpeg")
	public ResponseEntity<InputStreamResource> getImage(@RequestParam("imageName") @NotEmpty String imageName) throws Exception {
		InputStream inputStream = imageService.getImage(imageName);
		
	    return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpeg"))
	            .body(new InputStreamResource(inputStream));
	}
}