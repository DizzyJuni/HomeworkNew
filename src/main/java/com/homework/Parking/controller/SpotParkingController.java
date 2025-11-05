package com.homework.Parking.controller;

import com.homework.Parking.dto.response.client.ClientResponse;
import com.homework.Parking.dto.response.spotParking.SimpleSpotParkingResponse;
import com.homework.Parking.dto.response.spotParking.SpotParkingResponse;
import com.homework.Parking.entity.Client;
import com.homework.Parking.entity.SpotParking;
import com.homework.Parking.exception.SpotParkingException;
import com.homework.Parking.mapper.ClientMapper;
import com.homework.Parking.mapper.SpotParkingMapper;
import com.homework.Parking.repository.SpotParkingRepository;
import com.homework.Parking.service.SpotParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
public class SpotParkingController {

    private final SpotParkingRepository spotParkingRepository;
    private final SpotParkingService spotParkingService;
    private final SpotParkingMapper spotParkingMapper;
    private final ClientMapper clientMapper;

    @PostMapping("/occupy")
    public ResponseEntity<String> occupySpot(@RequestParam Long clientId, @RequestParam Integer floor,
                                             @RequestParam Integer spotNumber) {
        if (floor < 1 || floor > 4) {
            throw new SpotParkingException("Последний этаж 4.");
        }
        if (spotNumber < 1 || spotNumber > 10) {
            throw new SpotParkingException("Мест на этаже 10.");
        }

        SpotParking spot = spotParkingService.occupySpotParking(clientId, floor, spotNumber);

        // Добавь логирование для отладки
        System.out.println("После сохранения: " + spot.isBusy());
        System.out.println("Клиент: " + spot.getClient());

        return ResponseEntity.ok("Место успешно зарегистрировано на клиента.");
    }

    @PostMapping("/release/{spotId}")
    public ResponseEntity<String> releaseSpot(@PathVariable Long spotId) {
        SpotParking spot = spotParkingService.releaseSpotNumber(spotId);
        return ResponseEntity.ok("Место освобожденно.");
    }

    @GetMapping("/avalliable")
    public List<SpotParkingResponse> getAllAvalliableSpot() {
        return spotParkingService.getAvalliableSpot()
                .stream()
                .map(spotParkingMapper::toResponse)
                .toList();
    }

    @GetMapping("/avalliable/{floorId}")
    public List<SimpleSpotParkingResponse> getAvalliableSpotByFloor(@PathVariable Integer floorId) {
        if (floorId < 1 || floorId > 4) {
            throw new SpotParkingException("Последний этаж 4.");
        }
        return spotParkingService.getAvalliableSpotByFloor(floorId)
                .stream()
                .map(spotParkingMapper::toSimpleResponse)
                .toList();
    }

    @GetMapping("/client/{spotId}")
    public ClientResponse getClientBySpotId(@PathVariable Long spotId) {
        Client client = spotParkingService.getClientBySpotId(spotId);
        return clientMapper.toResponse(client);
    }

    @GetMapping("/price/{spotId}")
    public Integer getPriceBySpotId(@PathVariable Long spotId) {
        return spotParkingService.getPriceBySpotId(spotId);
    }
}
