package com.java.soap;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CelsiusToFahrenheit", namespace = "https://www.w3schools.com/xml/")
@XmlAccessorType(XmlAccessType.FIELD)
public class TempconvertRequest {

    @NotNull
    @Min(value=1, message="Please input more than 0")
    @XmlElement(name = "Celsius", namespace = "https://www.w3schools.com/xml/")
    private String celsius;

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}
