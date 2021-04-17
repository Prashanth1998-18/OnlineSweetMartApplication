package com.cg.osm.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address {
	@NotNull(message="City name cannot be null")
	private String city;
	@NotNull(message ="Contact Number cannot be null")
	@Size(min = 10,max = 15)
	private String contactNo;
	@NotNull(message="zip-code cannot be null")
	@Size(min = 6,max = 6)
	private int zip;

	public Address() {
	}// closing constructor

	public Address(@NotNull(message = "City name cannot be null") String city,
			@NotNull(message = "Contact Number cannot be null") @Size(min = 10, max = 15) String contactNo,
			@NotNull(message = "zip-code cannot be null") @Size(min = 6, max = 6) int zip) {
		this.city = city;
		this.contactNo = contactNo;
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getcontactNo() {
		return contactNo;
	}

	public void setcontactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getzip() {
		return zip;
	}

	public void setzip(int zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", contactNo=" + contactNo + ", zip=" + zip + "]";
	}

}