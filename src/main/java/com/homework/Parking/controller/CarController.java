package com.homework.Parking.controller;

import com.homework.Parking.dto.request.car.CreateCarRequest;
import com.homework.Parking.dto.response.car.CarResponse;
import com.homework.Parking.dto.response.car.SimpleCarResponse;
import com.homework.Parking.entity.Car;
import com.homework.Parking.exception.CarNotFoundException;
import com.homework.Parking.exception.ClientNotFoundException;
import com.homework.Parking.repository.CarRepository;
import com.homework.Parking.repository.ClientRepository;
import com.homework.Parking.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @PostMapping
    public ResponseEntity<String> createCar(@RequestBody @Valid CreateCarRequest request) {
        CarResponse response = carService.registerCar(request);
        return ResponseEntity.ok("Автомобиль успешно зарегистрирован.");
    }

    @PostMapping("/client/{clientId}/car/{carId}")
    public ResponseEntity<String> registerCarToClient(@PathVariable Long clientId, @PathVariable Long carId) {
        carService.registerCarToClient(clientId, carId);
        return ResponseEntity.ok("Машина успешно добавлена к клиенту");
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<String> saveCarToClient(@PathVariable Long clientId, @RequestBody @Valid CreateCarRequest request) {
        CarResponse response = carService.setCarToClient(clientId, request);
        return ResponseEntity.ok("Машина добавлена клиенту.");
    }

    @PostMapping("/client/{clientId}/batch")
    public ResponseEntity<String> saveCarsToClient(@PathVariable Long clientId, @RequestBody List<CreateCarRequest> requests) {
        List<CarResponse> savedCars = carService.setCarsToClient(clientId, requests);
        return ResponseEntity.ok("Машины добавлены клиенту.");
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<SimpleCarResponse>> getCarByClientId(@PathVariable Long clientId) {
        List<SimpleCarResponse> clientCars = carService.getClientCars(clientId);
        return ResponseEntity.ok(clientCars);
    }

    @GetMapping("/by-number")
    public ResponseEntity<Car> getCarByNumber(@RequestParam String number) {
        Car savedCar = carService.getCarByNumber(number);
        return ResponseEntity.ok(savedCar);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<String> updateCarById(@PathVariable Long carId, @RequestBody @Valid CreateCarRequest request) {
        carService.updateCarById(carId, request);
        return ResponseEntity.ok("Машина успешно изменена.");
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCarById(@PathVariable Long carId) {
        try {
            carService.deleteById(carId);
            return ResponseEntity.ok("Машина успешно удалена.");
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/by-number/{number}")
    public ResponseEntity<String> deleteByCarNumber(@PathVariable String number) {
        try {
            carService.deleteCarByNumber(number);
            return ResponseEntity.ok("Машина с номером " + number + " удалена.");
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/owner/{ownerId}")
    public ResponseEntity<String> deleteAllCarsByClientId(@PathVariable Long ownerId) {
        try {
            carService.deleteCarByClientId(ownerId);
            return ResponseEntity.ok("Машины клиента с ID " + ownerId + " успешно удалены.");
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
