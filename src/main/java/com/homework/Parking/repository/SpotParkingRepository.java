package com.homework.Parking.repository;

import com.homework.Parking.entity.SpotParking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotParkingRepository extends JpaRepository<SpotParking, Long> {

    Optional<SpotParking> findByFloorAndSpotNumberAndIsBusyFalse(Integer floor, Integer spotNumber);
    List<SpotParking> findByFloorAndIsBusyFalse(Integer floor);
    List<SpotParking> findByIsBusyFalse();
    Optional<SpotParking> findByIdAndIsBusyTrue(Long spotId);
}