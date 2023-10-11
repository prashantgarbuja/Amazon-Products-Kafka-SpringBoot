package com.prashantgarbuja.warehouse.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prashantgarbuja.warehouse.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@AllArgsConstructor
@Slf4j
public class ProductReceiver {
	
	private final EntityManager entityManager;
	
	@Transactional
	@KafkaListener(topics = "amazon-products-delivery", concurrency = "5")
	public void listen(ConsumerRecord<String, String> record) {
		final var deliveredProduct = parseFromString(record);
		
		// Making the consumer idempotent
		if (entityManager.find(Product.class, deliveredProduct.getId()) == null) {
			entityManager.persist(deliveredProduct);
			log.info("Successfully saved {}", record.value());
		} else {
			entityManager.merge(deliveredProduct);
			log.info("Successfully updated {}", record.value());
		}
	}

	private Product parseFromString(ConsumerRecord<String, String> record) {
		try {
			return new ObjectMapper().registerModule(new JavaTimeModule()).readValue(record.value(), Product.class);
		} catch (final JsonProcessingException exception) {
			throw new RuntimeException("Unable to parse message " + record.value(), exception);
		}
	}

}
