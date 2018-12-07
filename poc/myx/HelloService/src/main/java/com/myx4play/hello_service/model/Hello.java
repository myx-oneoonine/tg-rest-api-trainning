package com.myx4play.hello_service.model;

public class Hello {
	private String firstName;
	private String lastName;

	private String fullName;

	public Hello(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName() {
		this.fullName = this.firstName + " " + this.lastName;
	}

}
