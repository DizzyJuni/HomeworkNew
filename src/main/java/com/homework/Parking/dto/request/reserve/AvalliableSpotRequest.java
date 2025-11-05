package com.homework.Parking.dto.request.reserve;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvalliableSpotRequest {

    @NotNull
    private LocalDateTime start;
    private LocalDateTime end;
}

