package com.model.onetomany.reverse;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="page")
public class Page {

	@Id
	@GeneratedValue
	@Column(name="page_id")
	private Long pageId;
	
	@Column(name="name")
	private String pageName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="isActive")
	private Integer isActive;
	
	@Column(name="updatedDate")
	private Date updatedDate = new Date();
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.EXCEPTION)
	private Platform platform;
	
	
	
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
