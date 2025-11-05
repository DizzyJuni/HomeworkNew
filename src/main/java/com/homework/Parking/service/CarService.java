package com.homework.Parking.service;

import com.homework.Parking.dto.request.car.CreateCarRequest;
import com.homework.Parking.dto.response.car.CarResponse;
import com.homework.Parking.dto.response.car.SimpleCarResponse;
import com.homework.Parking.entity.Car;
import com.homework.Parking.entity.Client;
import com.homework.Parking.exception.CarNotFoundException;
import com.homework.Parking.exception.ClientNotFoundException;
import com.homework.Parking.exception.ValidationException;
import com.homework.Parking.mapper.CarMapper;
import com.homework.Parking.repository.CarRepository;
import com.homework.Parking.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CarMapper carMapper;

    public CarResponse registerCar(CreateCarRequest request) {
        Car car = carMapper.toEntity(request);
        if (carRepository.existsByNumber(car.getNumber())) {
            throw new ValidationException("Автомобиль с таким гос.номером зарегистрирован");
        }
        carRepository.save(car);
        return carMapper.toResponse(car);
    }

    public CarResponse setCarToClient(Long clientId, CreateCarRequest request) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
        Car car = carMapper.toEntity(request);

        if (carRepository.existsByNumber(car.getNumber())) {
            throw new ValidationException("Автомобиль с таким гос.номером зарегистрирован");
        }

        car.setOwner(client);
        Car savedCar = carRepository.save(car);
        return carMapper.toResponse(savedCar);
    }

    public CarResponse registerCarToClient(Long clientId, Long carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с ID " + clientId + " не найден."));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Машина с ID " + carId + " не найдена."));

        if (car.getOwner() != null) {
            String message = car.getOwner().getId().equals(clientId)
                    ? "Машина уже добавлена к этому клиенту"
                    : "Машина уже привязана к другому клиенту";
            throw new ValidationException(message);
        }

        car.setOwner(client);
        Car carToUpdate = carRepository.save(car);
        return carMapper.toResponse(carToUpdate);
    }

    public List<CarResponse> setCarsToClient(Long clientId, List<CreateCarRequest> requests) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
        List<Car> cars = requests.stream()
                .map(carMapper::toEntity)
                .peek(car -> car.setOwner(client))
                .collect(Collectors.toList());

        List<Car> carSaved = carRepository.saveAll(cars);

        return carSaved.stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<SimpleCarResponse> getClientCars(Long clientId) {
        List<Car> cars = carRepository.findByOwnerId(clientId);
        return cars.stream()
                .map(carMapper::toResponseSimple)
                .collect(Collectors.toList());
    }

    public Car getCarByNumber(String number) {
        return carRepository.findByNumber(number);
    }

    public CarResponse updateCarById(Long id, CreateCarRequest request) {
        Car currentCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Машина не найдена"));

        if (!request.getNumber().equals(currentCar.getNumber()) &&
                carRepository.existsByNumber(request.getNumber())) {
            throw new ValidationException("Гос номер уже зарегистрирован.");
        }
        currentCar.setBrand(request.getBrand());
        currentCar.setModel(request.getModel());
        currentCar.setColour(request.getColour());
        currentCar.setNumber(request.getNumber());
        currentCar.setYear(request.getYear());

        return carMapper.toResponse(carRepository.save(currentCar));
    }

    public void deleteById(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Машина с ID " + id + " не найдена");
        }
        carRepository.deleteById(id);
    }

    public void deleteCarByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        if (car == null) {
            throw new CarNotFoundException("Машина с номером " + number + " не найдена");
        }
        carRepository.delete(car);
    }

    public void deleteCarByClientId(Long ownerId) {
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(ownerId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с ID " + ownerId + " не найден")));
        carRepository.deleteAllCarsByOwnerId(ownerId);
    }
}
