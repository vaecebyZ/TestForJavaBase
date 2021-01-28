package com.sybinal.shop.model;

public class Invoice {
	private Integer id;
	private Integer type;
	private Integer uid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	private String cpName;
	private String cpNum;
	private String address;
	private String phone;

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", type=" + type + ", uid=" + uid + ", cpName=" + cpName + ", cpNum=" + cpNum
				+ ", address=" + address + ", phone=" + phone + ", bankName=" + bankName + ", bankId=" + bankId + "]";
	}
	private String bankName;
	private String bankId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getCpNum() {
		return cpNum;
	}
	public void setCpNum(String cpNum) {
		this.cpNum = cpNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	
}
