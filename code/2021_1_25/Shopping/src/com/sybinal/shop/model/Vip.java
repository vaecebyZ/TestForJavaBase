package com.sybinal.shop.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Vip {
	
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	private String userName;
	private String nickname;
	private String vipName;
	private Integer userId;
	private String email;
	private String image;
	private String registerTimeFrom;
	private String registerTimeTo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegisterTimeFrom() {
		return registerTimeFrom;
	}
	public void setRegisterTimeFrom(String registerTimeFrom) {
		this.registerTimeFrom = registerTimeFrom;
	}
	public String getRegisterTimeTo() {
		return registerTimeTo;
	}
	public void setRegisterTimeTo(String registerTimeTo) {
		this.registerTimeTo = registerTimeTo;
	}
	
	
	
	
	
	
	
	
	
	

}
