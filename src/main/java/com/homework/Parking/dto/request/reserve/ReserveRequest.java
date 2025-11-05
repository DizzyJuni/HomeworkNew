package com.homework.Parking.dto.request.reserve;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReserveRequest {

    @NotBlank
    private Long clientId;

    @NotBlank
    private Long spotId;

    @NotBlank
    private LocalDateTime start;

    private LocalDateTime end;
}
