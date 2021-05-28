package com.learnspring.SpringBoot24.helloworld;

public class HelloWorld {

	private String name;
	private String message;
	
	@Override
	public String toString() {
		return "HelloWorld [name=" + name + ", message=" + message + "]";
	}
	
	public HelloWorld(String message) {
		this.message = message;
	}
	public HelloWorld(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
