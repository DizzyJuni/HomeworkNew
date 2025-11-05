package com.homework.Parking.mapper;

import com.homework.Parking.dto.request.car.CreateCarRequest;
import com.homework.Parking.dto.response.car.CarResponse;
import com.homework.Parking.dto.response.car.SimpleCarResponse;
import com.homework.Parking.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car toEntity(CreateCarRequest request) {
        Car car = new Car();
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setColour(request.getColour());
        car.setNumber(request.getNumber());
        car.setYear(request.getYear());
        return car;
    }

    public CarResponse toResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setBrand(car.getBrand());
        response.setModel(car.getModel());
        response.setColour(car.getColour());
        response.setNumber(car.getNumber());
        response.setYear(car.getYear());

        if (car.getOwner() != null) {
            response.setOwnerId(car.getOwner().getId());
            response.setOwnerName(car.getOwner().getName());
        } else {
            response.setOwnerId(null);
            response.setOwnerName(null);
        }
        return response;
    }

    public SimpleCarResponse toResponseSimple (Car car) {
        SimpleCarResponse response = new SimpleCarResponse();
        response.setBrand(car.getBrand());
        response.setModel(car.getModel());
        response.setColour(car.getColour());
        response.setNumber(car.getNumber());
        response.setYear(car.getYear());

        return response;
    }
}
