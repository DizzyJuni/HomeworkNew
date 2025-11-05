package com.homework.Parking.controller;

import com.homework.Parking.dto.request.reserve.AvalliableSpotRequest;
import com.homework.Parking.dto.request.reserve.ReservePriceRequest;
import com.homework.Parking.dto.request.reserve.ReserveRequest;
import com.homework.Parking.dto.response.reserve.ReservePriceResponse;
import com.homework.Parking.dto.response.reserve.ReserveResponse;
import com.homework.Parking.dto.response.spotParking.SpotParkingResponse;
import com.homework.Parking.entity.Reserve;
import com.homework.Parking.exception.SpotParkingException;
import com.homework.Parking.service.ReserveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping
    public ResponseEntity<?> createReserve(@RequestBody ReserveRequest request) {
        try {
            Reserve reserve = reserveService.createReserve(
                    request.getClientId(),
                    request.getSpotId(),
                    request.getStart(),
                    request.getEnd()
            );
            return ResponseEntity.ok("Резерв успешно создан.");
        } catch (SpotParkingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancel/{reserveId}")
    public ResponseEntity<String> cancelReserveById(@PathVariable Long reserveId) {
        if (!reserveService.getReserveById(reserveId).getIsActive()) {
            throw new SpotParkingException("Резерв не найден.");
        }
        try {
            reserveService.cancelReserve(reserveId);
            return ResponseEntity.ok("Резерв отменен.");
        } catch (SpotParkingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/calculate-price")
    public ReservePriceResponse calculatePrice(@Valid @RequestBody ReservePriceRequest request) {
        return reserveService.calculateReservePrice(
                request.getSpotId(),
                request.getStart(),
                request.getEnd());
    }

    @GetMapping("/{reserveId}")
    public ReserveResponse getReserveById(@PathVariable Long reserveId) {
        return reserveService.getReserveResponseById(reserveId);
    }

    @GetMapping("/all")
    public List<ReserveResponse> getAllReserveIsActive() {
        return reserveService.getAllReserveIsActive();
    }

    @GetMapping("/client/{clientId}")
    public List<ReserveResponse> getReserveByClientId(@PathVariable Long clientId) {
        return reserveService.getActiveReserveByClientId(clientId);
    }

    @GetMapping("/avalliableSpot")
    public List<SpotParkingResponse> getAllAvalliableSpotForReserve(@RequestBody AvalliableSpotRequest request) {
        return reserveService.getAllFreedomSpotForReserve(request.getStart(), request.getEnd());
    }
}
