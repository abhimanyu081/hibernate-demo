package com.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "USER_DETAILS")
public class UserDetails {
	


	@Id // primary key of the object
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String name;

	@Lob
	private String description;

	@Temporal(TemporalType.TIME)
	private Date joinedDate;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@GenericGenerator(name ="my-generator", strategy = "increment")
	@JoinTable(name="USER_ADDRESS", joinColumns=@JoinColumn(name = "USER_ID"))
	@CollectionId(columns= {@Column(name = "ADDRESS_D")},generator="my-generator", type=@Type(type="long"))
	private Collection<Address> listOfAddresses = new ArrayList<>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}

	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", name=" + name + ", description=" + description + ", joinedDate="
				+ joinedDate + ", listOfAddresses=" + listOfAddresses + "]";
	}

	





}
