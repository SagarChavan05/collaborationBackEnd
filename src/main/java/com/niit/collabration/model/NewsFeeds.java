package com.niit.collabration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "newsFeed")
@Component
public class NewsFeeds {

	@Id
	@Column(name = "nfid")
	private int NfId;

	@Column(name = "discription")
	private String discription;

	@Column(name = "imgfpath")
	private String imgfpath;

	public int getNfId() {
		return NfId;
	}

	public void setNfId(int nfId) {
		NfId = nfId;
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

}