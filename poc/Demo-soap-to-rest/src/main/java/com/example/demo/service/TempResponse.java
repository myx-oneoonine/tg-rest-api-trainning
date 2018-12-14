package com.example.demo.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CelsiusToFahrenheitResponse", namespace = "https://www.w3schools.com/xml/")
@XmlAccessorType(XmlAccessType.FIELD)
public class TempResponse {

    @XmlElement(name = "CelsiusToFahrenheitResult", namespace = "https://www.w3schools.com/xml/")
	private String farenheit;

	public String getFarenheit() {
		return farenheit;
	}

	public void setFarenheit(String farenheit) {
		this.farenheit = farenheit;
	}

}
