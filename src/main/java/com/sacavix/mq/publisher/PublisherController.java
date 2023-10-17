package com.sacavix.mq.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq/kyc")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@PostMapping
	public void sendImage(@RequestBody Image image) {
		this.publisherService.sendToRabbit(image);
	}

}
