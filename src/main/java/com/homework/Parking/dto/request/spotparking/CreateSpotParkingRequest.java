package com.homework.Parking.dto.request.spotparking;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSpotParkingRequest {

    @NotBlank (message = "Укажите этаж.")
    @Max (value = 4, message = "Последний этаж 4.")
    private Integer floor;

    @NotBlank (message = "Укажите место.")
    @Max( value = 10, message = "Максимальное место на этаже 10.")
    private Integer spotNumber;
}
