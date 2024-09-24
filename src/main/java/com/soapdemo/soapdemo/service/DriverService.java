package com.soapdemo.soapdemo.service;

import com.example.soap.Driver;
import com.example.soap.DriversList;
import com.example.soap.GetDriversByLocationResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soapdemo.soapdemo.dto.DriverDto;
import com.soapdemo.soapdemo.dto.DriverRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DriverService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    public DriverService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    public GetDriversByLocationResponse getDriversByLocation(String location) {
        try {
//            // Create the request object
//            DriverRequest request = new DriverRequest();
//            request.setLocation(location);
//
//            // Convert the Java object to JSON
//            String jsonRequest = objectMapper.writeValueAsString(request);

            String jsonResponse = webClient.get()
                    .uri("/api/v1/location?location={location}", location)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Convert JSON response to a List<DriverDto>
            List<DriverDto> driverDtos = objectMapper.readValue(jsonResponse, new TypeReference<List<DriverDto>>() {});


            DriversList driversList = new DriversList();
            for (DriverDto driverDto : driverDtos) {

                Driver soapDriver = new Driver();
                soapDriver.setDriverName(driverDto.getDriverName());
                soapDriver.setDriverEmail(driverDto.getDriverEmail());
                soapDriver.setDriverLocation(driverDto.getDriverLocation());

                driversList.getDriver().add(soapDriver);
            }

            GetDriversByLocationResponse soapResponse = new GetDriversByLocationResponse();
            soapResponse.setDrivers(driversList);
            soapResponse.setMessage("Success");

            return soapResponse;
        } catch (Exception e) {
            throw new RuntimeException("Error processing request: " + e.getMessage(), e);
        }
    }
}
