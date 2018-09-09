package com.hibernate.crud.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class UserDetails {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	
	
	public UserDetails(String name) {
		super();
		this.name = name;
	}
	
	public UserDetails() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
