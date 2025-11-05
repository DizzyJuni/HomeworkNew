package com.homework.Parking.service;

import com.homework.Parking.entity.Client;
import com.homework.Parking.entity.SpotParking;
import com.homework.Parking.exception.ClientNotFoundException;
import com.homework.Parking.exception.SpotParkingException;
import com.homework.Parking.mapper.ClientMapper;
import com.homework.Parking.repository.ClientRepository;
import com.homework.Parking.repository.SpotParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotParkingService {

    private final ClientRepository clientRepository;
    private final SpotParkingRepository spotParkingRepository;
    private final ClientMapper mapper;

    public SpotParking occupySpotParking(Long clientId, Integer floor, Integer spotNumber) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден."));
        SpotParking spot = spotParkingRepository.findByFloorAndSpotNumberAndIsBusyFalse(floor, spotNumber)
                .orElseThrow(() -> new SpotParkingException("Место занято или не найдено."));

        spot.setClient(client);
        spot.setBusy(true);

        return spotParkingRepository.save(spot);
    }

    public SpotParking releaseSpotNumber(Long spotId) {
        SpotParking spot = spotParkingRepository.findById(spotId)
                .orElseThrow(() -> new SpotParkingException("Место не найдено."));
        if (!spot.isBusy()) {
            throw new SpotParkingException("Место было свободно.");
        }
        spot.setClient(null);
        spot.setBusy(false);

        return spotParkingRepository.save(spot);
    }

    public List<SpotParking> getAvalliableSpot() {
        return spotParkingRepository.findByIsBusyFalse();
    }

    public List<SpotParking> getAvalliableSpotByFloor(Integer floorId) {
        return spotParkingRepository.findByFloorAndIsBusyFalse(floorId);
    }

    public Client getClientBySpotId(Long spotId) {
        SpotParking spot = spotParkingRepository.findByIdAndIsBusyTrue(spotId)
                .orElseThrow(() -> new SpotParkingException("Место свободно."));
        return spot.getClient();
    }

    public Integer getPriceBySpotId(Long spotId) {
        SpotParking spot = spotParkingRepository.findById(spotId)
                .orElseThrow(() -> new SpotParkingException("Место не найдено."));
        return spot.getPrice().getPrice();
    }
}
