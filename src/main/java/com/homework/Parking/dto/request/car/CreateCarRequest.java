package com.homework.Parking.dto.request.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateCarRequest {
    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String colour;

    @NotBlank
    @Pattern(regexp = "^[А-Я]\\d{3}[А-Я]{2}\\d{2,3}$" , message = "Гос номер должен быть без пробелов и формата А111АА11 или А111АА111. " +
            "Используйте кириллицу.")
    private String number;

    @NotNull
    private Integer year;
}
