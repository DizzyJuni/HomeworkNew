package com.homework.Parking.repository;

import com.homework.Parking.entity.Client;
import com.homework.Parking.entity.Reserve;
import com.homework.Parking.entity.SpotParking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    List<Reserve> findBySpotAndIsActiveTrue(SpotParking spot);

    List<Reserve> findByClientAndIsActiveTrue(Client client);

    List<Reserve> findByIsActiveTrue();

    @Query("SELECT COUNT(r) > 0 FROM Reserve r WHERE r.spot = :spot AND r.isActive = true " +
            "AND ((r.startReserve <= :end AND r.endReserve >= :start))")
    boolean existsActiveReservationForSpot(@Param("spot") SpotParking spot,
                                           @Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end);

    @Query("SELECT s FROM SpotParking s WHERE s.isBusy = false AND NOT EXISTS (SELECT r FROM Reserve r WHERE r.spot = s AND r.isActive = true" +
            " AND r.startReserve <= :end AND r.endReserve >= :start)")
    List<SpotParking> findSpotsAvailableForReservation(@Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);
}