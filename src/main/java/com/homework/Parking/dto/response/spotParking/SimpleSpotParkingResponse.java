package com.homework.Parking.dto.response.spotParking;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SimpleSpotParkingResponse {

    @NotBlank(message = "Укажите номер парковки на этаже.")
    private Integer spotNumber;
}
