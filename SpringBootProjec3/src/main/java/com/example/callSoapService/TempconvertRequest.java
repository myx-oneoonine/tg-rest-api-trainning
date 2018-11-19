package com.example.callSoapService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TempconvertRequest {

    @NotNull
    @Min(value=1, message="Please input more than 0")
    private String celsius;

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}
