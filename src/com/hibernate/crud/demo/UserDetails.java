package com.hibernate.crud.demo;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
@NamedQuery(name="UserDetailsByID", query="from UserDetails where ID =?")
@NamedNativeQuery(name="UserDetails.byName",query="select * from users_detais where username = ?",resultClass=UserDetails.class)
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
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
