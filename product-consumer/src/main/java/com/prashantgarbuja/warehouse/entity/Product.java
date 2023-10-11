package com.prashantgarbuja.warehouse.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private UUID id;
	
	private String productName;
	private String price;
	private String category;
	private String description;
	private LocalDateTime boughtTime;
	private LocalDateTime deliveredTime;
	
	@PrePersist
	void currentTime() {
		ZoneId zoneIdAEST = ZoneId.of("Australia/Sydney");
		this.deliveredTime = LocalDateTime.now(zoneIdAEST); //not explicitly mentioning UTC +11:00 due to Daylight saving.
	}

}
