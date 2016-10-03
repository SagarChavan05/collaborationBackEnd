package com.niit.collabration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "BLOG")
@Component
public class Blog {

	@Id
	@Column(name = "blogid")
	private int blogId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "discription")
	private String discription;
	
	@Column(name = "imgfpath")
	private String imgfpath;
	
	@Column(name = "status")
	private boolean status;

	public int getBlgoId() {
		return blogId;
	}

	public void setBlgoId(int blgoId) {
		this.blogId = blgoId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getImgfpath() {
		return imgfpath;
	}

	public void setImgfpath(String imgfpath) {
		this.imgfpath = imgfpath;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}