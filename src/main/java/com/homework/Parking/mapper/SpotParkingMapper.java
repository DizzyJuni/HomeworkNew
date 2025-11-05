package com.homework.Parking.mapper;

import com.homework.Parking.dto.request.spotparking.CreateSpotParkingRequest;
import com.homework.Parking.dto.response.spotParking.SimpleSpotParkingResponse;
import com.homework.Parking.dto.response.spotParking.SpotParkingResponse;
import com.homework.Parking.entity.SpotParking;
import org.springframework.stereotype.Component;

@Component
public class SpotParkingMapper {

    public SpotParking toEntity(CreateSpotParkingRequest request) {
        SpotParking spot = new SpotParking();
        spot.setFloor(request.getFloor());
        spot.setSpotNumber(request.getSpotNumber());

        return spot;
    }

    public SpotParkingResponse toResponse(SpotParking spot) {
        SpotParkingResponse response = new SpotParkingResponse();
        response.setFloor(spot.getFloor());
        response.setSpotNumber(spot.getSpotNumber());

        return response;
    }

    public SimpleSpotParkingResponse toSimpleResponse(SpotParking spot) {
        SimpleSpotParkingResponse response = new SimpleSpotParkingResponse();
        response.setSpotNumber(spot.getSpotNumber());

        return response;
    }
}
