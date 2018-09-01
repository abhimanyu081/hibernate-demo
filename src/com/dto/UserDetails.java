package com.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "USER_DETAILS") //
public class UserDetails {

	@Id // primary key of the object
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String name;

	@Lob
	private String description;

	@Temporal(TemporalType.TIME)
	private Date joinedDate;

	@Embedded
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({
		
		@AttributeOverride(name = "street", column = @Column(name = "home_street")),
		@AttributeOverride(name = "city", column = @Column(name = "home_city")),
		@AttributeOverride(name = "state", column = @Column(name = "home_state")),
		@AttributeOverride(name = "pincode", column = @Column(name = "home_pincode")),
	
	})
	// modify defssult behaviour
	private Address officeAddress;

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



	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
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

	

}
