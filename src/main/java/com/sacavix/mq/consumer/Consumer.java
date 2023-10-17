package com.sacavix.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sacavix.mq.publisher.Image;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
public class Consumer {

	@Autowired
	private RestTemplate restTemplate;

	@RabbitListener(queues = { "${sacavix.queue.name}" })
	public void receive(@Payload Image image) {
		log.info("mensaje recibido", image);
		sendToKYCMicroservice(image);

	}

	private void sendToKYCMicroservice(Image image) {
		String kycServiceUrl = "http://localhost:3000/verification";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Crear un objeto HttpEntity con el objeto Image en el cuerpo
		HttpEntity<Image> requestEntity = new HttpEntity<>(image, headers);

		ResponseEntity<Void> response = restTemplate.exchange(
				kycServiceUrl, HttpMethod.POST, requestEntity, Void.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info("Image sent to KYC microservice successfully");
		} else {
			log.error("Failed to send image to KYC microservice. Status code: " + response.getStatusCode());
		}

	}

}
