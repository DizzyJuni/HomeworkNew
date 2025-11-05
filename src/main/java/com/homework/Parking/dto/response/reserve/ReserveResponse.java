package com.homework.Parking.dto.response.reserve;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReserveResponse {
    private String nameClient;
    private Integer floor;
    private Integer numberSpot;
    private LocalDateTime start;
    private LocalDateTime end;
}
