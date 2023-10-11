package com.prashantgarbuja.warehouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashantgarbuja.warehouse.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductController {
	
	private final EntityManager entityManager;
	
	@GetMapping(value = "/products")
	public List<Product> getProduct() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		CriteriaQuery<Product> productListQuery = criteriaQuery.select(criteriaQuery.from(Product.class));
		final List<Product> products = entityManager.createQuery(productListQuery).getResultList();
		return products;
	}

}
