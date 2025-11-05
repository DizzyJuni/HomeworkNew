package com.homework.Parking.dto.response.car;

import lombok.Data;

@Data
public class CarResponse {
    private String brand;
    private String model;
    private String colour;
    private String number;
    private Integer year;
    private Long ownerId;
    private String ownerName;
}
