package de.kozdemir.myClient;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product implements Serializable {
	private int id; 
	private String name;
	private String description;
	private int amount;
	private double price;
	private LocalDate createdAt;
	
	private static int count; //Class Variable count

	public Product() {
		createdAt = LocalDate.now();
		setCreatedAt(createdAt);
		setId(++count);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [name=").append(name).append(", description=").append(description).append(", price=")
				.append(price).append(", createdAt=").append(createdAt).append("]\n");
		
		return builder.toString();
	}


}