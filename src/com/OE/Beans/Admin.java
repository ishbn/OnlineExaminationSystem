package com.OE.Beans;

public class Admin {
	Integer admin_id;
	String adminName,sex,cardNumber,phone;
	public Admin() {
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer aid) {
		this.admin_id = aid;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String aname) {
		this.adminName = aname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
