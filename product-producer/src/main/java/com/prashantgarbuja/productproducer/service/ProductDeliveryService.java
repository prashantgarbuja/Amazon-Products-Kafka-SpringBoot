package com.prashantgarbuja.productproducer.service;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prashantgarbuja.productproducer.dto.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProductDeliveryService {
	
	private final KafkaTemplate<Null, String> kafkaTemplate;
	
	public void deliverProduct (final Product product) {
		//System.out.println("Without parsing " + product);
		String message = parseAsString(product);
		//System.out.println("After parsing " + message);;
		
		CompletableFuture<SendResult<Null, String>> future = kafkaTemplate.sendDefault(message);
	     future.whenComplete((result, ex) -> {
	         if (ex == null) {
	             log.info("Delivered product=[" + message + 
	                 "] with offset=[" + result.getRecordMetadata().offset() + 
	                 "] to partition=["+ result.getRecordMetadata().partition() +"]");
	         } else {
	             log.error("Unable to send message=[" + 
	                 message + "] due to : " + ex.getMessage());
	         }
	     });
	}

	private String parseAsString(Product product) {
		String message = null;
		try {
			message = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product);
		} catch (JsonProcessingException e) {
			new RuntimeException("Unable to deliver product " + product.toString(), e);
		}
		return message;
	}

}
