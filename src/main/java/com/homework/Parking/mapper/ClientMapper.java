package com.homework.Parking.mapper;

import com.homework.Parking.dto.request.client.CreateClientRequest;
import com.homework.Parking.dto.response.client.ClientResponse;
import com.homework.Parking.dto.response.client.SimpleClientResponse;
import com.homework.Parking.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final CarMapper carMapper;

    public Client toEntity(CreateClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setAge(request.getAge());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setEmail(request.getEmail());
        return client;
    }

    public ClientResponse toResponse(Client client) {
        ClientResponse response = new ClientResponse();
        response.setName(client.getName());
        response.setAge(client.getAge());
        response.setPhoneNumber(client.getPhoneNumber());
        response.setEmail(client.getEmail());

        response.setCars(client.getCars() != null ?
                client.getCars().stream()
                        .map(carMapper::toResponseSimple)
                        .collect(Collectors.toList()) :
                Collections.emptyList());

        return response;
    }

    public SimpleClientResponse toResponseSimple(Client client) {
        SimpleClientResponse response = new SimpleClientResponse();
        response.setName(client.getName());
        response.setAge(client.getAge());
        response.setPhoneNumber(client.getPhoneNumber());
        response.setEmail(client.getEmail());

        return response;
    }
}
