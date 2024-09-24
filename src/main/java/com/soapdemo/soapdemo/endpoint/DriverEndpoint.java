package com.soapdemo.soapdemo.endpoint;

import com.example.soap.GetDriversByLocationRequest;
import com.example.soap.GetDriversByLocationResponse;
import com.soapdemo.soapdemo.service.DriverService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DriverEndpoint {

    private static final String NAMESPACE_URI = "http://soap.example.com";

    private final DriverService driverService;

    public DriverEndpoint(DriverService driverService) {
        this.driverService = driverService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDriversByLocationRequest")
    @ResponsePayload
    public GetDriversByLocationResponse getDriversByLocation(@RequestPayload GetDriversByLocationRequest request) {
        String location = request.getLocation();
        GetDriversByLocationResponse response = driverService.getDriversByLocation(location);
        return response;
    }

}