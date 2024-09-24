package com.soapdemo.soapdemo.dto;

import java.util.List;

public class DriverResponse {
    private List<DriverDto> data;
    private String message;

    public DriverResponse(List<DriverDto> data) {
        this.data = data;
    }

    public List<DriverDto> getData() {
        return data;
    }

    public void setData(List<DriverDto> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
