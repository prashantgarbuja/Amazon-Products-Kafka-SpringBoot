package com.prashantgarbuja.productproducer.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.prashantgarbuja.productproducer.dto.Product;
import com.prashantgarbuja.productproducer.service.ProductDeliveryService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableScheduling
public class ProductBought {
	
	private final ProductDeliveryService productDeliveryService;
	
	@Scheduled(fixedRate = 5000)
	public void bought() {
		//Every 5sec a product is bought
		final var product = Product.buyProduct();
		productDeliveryService.deliverProduct(product);
	}

}
