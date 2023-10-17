package com.sacavix.mq.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PublisherService {

	@Autowired
	private Publisher publisher;

	public void sendToRabbit(Image image) {
		Image img = new Image(image);
		log.info("Message '{}' will be send ... ", img);
		this.publisher.send(img);
	}

}
