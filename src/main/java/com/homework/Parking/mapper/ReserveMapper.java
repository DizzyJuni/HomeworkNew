package com.homework.Parking.mapper;

import com.homework.Parking.dto.response.reserve.ReserveResponse;
import com.homework.Parking.entity.Reserve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReserveMapper {

    public ReserveResponse toResponse(Reserve reserve) {
        ReserveResponse response = new ReserveResponse();
        response.setNameClient(reserve.getClient().getName());
        response.setFloor(reserve.getSpot().getFloor());
        response.setNumberSpot(reserve.getSpot().getSpotNumber());
        response.setStart(reserve.getStartReserve());
        response.setEnd(reserve.getEndReserve());
        return response;
    }
}
