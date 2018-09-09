package com.hibernate.cascade.demo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
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

	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="address_id")
	private Collection<Address> deliveryAddresses = new ArrayList<>();

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

	public Collection<Address> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	public void setDeliveryAddresses(Collection<Address> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}
	
	
	
}
