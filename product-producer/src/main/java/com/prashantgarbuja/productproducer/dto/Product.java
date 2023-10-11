package com.prashantgarbuja.productproducer.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import com.github.javafaker.Faker;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Product {
	
	private UUID id;
	private String productName;
	private String price;
	private String category;
	private String description;
	private LocalDateTime boughtTime;
	
	public static Product buyProduct() {
		final var product = new Faker().commerce();
		return Product.builder()
				.id(UUID.randomUUID())
				.productName(product.productName())
				.price(product.price())
				.category(product.department())
				.description(product.material())
				.boughtTime(LocalDateTime.now(ZoneId.of("Australia/Sydney")))
				.build();
	    }
	}
