package com.learnspring.SpringBoot24.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("someBeanFilter")
public class SomeBean {
	private String homename;
	private String address1;
	private String city;
	public SomeBean(String homename, String address1, String city) {
		super();
		this.homename = homename;
		this.address1 = address1;
		this.city = city;
	}
	public String getHomename() {
		return homename;
	}
	public void setHomename(String homename) {
		this.homename = homename;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
