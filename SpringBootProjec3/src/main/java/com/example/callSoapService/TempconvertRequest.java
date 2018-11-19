package com.example.callSoapService;

import javax.validation.constraints.NotNull;

public class TempconvertRequest {

    @NotNull
    private String celsius;

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}
