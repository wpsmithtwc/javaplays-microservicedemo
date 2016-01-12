package com.ibm.ecoddemo.microservice.accountsui;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author danielcho
 *
 */
@JsonRootName("Customer")
public class CustomerDTO {

	private Long id;
	private String customerNo;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	private String email;

	public CustomerDTO() {
		/* setup default values */
		id = Long.valueOf(0);
		customerNo = "";
		name = "";
		address = "";
		city = "";
		state = "";
		zipCode = "";
		phone = "";
		email = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
