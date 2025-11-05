package com.homework.Parking.dto.request.reserve;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservePriceRequest {

    @NotNull(message = "Поле не может быть пустым.")
    private Long spotId;

    @NotNull(message = "Поле не может быть пустым.")
    private LocalDateTime start;

    @NotNull(message = "Поле не может быть пустым.")
    private LocalDateTime end;
}

