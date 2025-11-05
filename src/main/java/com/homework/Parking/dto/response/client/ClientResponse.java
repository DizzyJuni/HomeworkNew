package com.homework.Parking.dto.response.client;

import com.homework.Parking.dto.response.car.SimpleCarResponse;
import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    private List<SimpleCarResponse> cars;
}
