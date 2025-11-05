package com.homework.Parking.repository;

import com.homework.Parking.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByOwnerId(Long clientId);
    Car findByNumber(String number);
    boolean existsByNumber(String number);
    void deleteAllCarsByOwnerId(Long ownerId);
}