package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Customer {

	@Id
	private ObjectId _id;
	private String name;
	private String email;
	private String description;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(ObjectId id, String name, String email, String description) {
		super();
		this._id = id;
		this.name = name;
		this.email = email;
		this.description = description;
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId id) {
		this._id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
