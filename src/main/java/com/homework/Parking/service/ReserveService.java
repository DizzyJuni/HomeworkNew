package com.homework.Parking.service;

import com.homework.Parking.dto.response.reserve.ReservePriceResponse;
import com.homework.Parking.dto.response.reserve.ReserveResponse;
import com.homework.Parking.dto.response.spotParking.SpotParkingResponse;
import com.homework.Parking.entity.Client;
import com.homework.Parking.entity.Reserve;
import com.homework.Parking.entity.SpotParking;
import com.homework.Parking.enums.Discount;
import com.homework.Parking.exception.ClientNotFoundException;
import com.homework.Parking.exception.SpotParkingException;
import com.homework.Parking.mapper.ReserveMapper;
import com.homework.Parking.mapper.SpotParkingMapper;
import com.homework.Parking.repository.ClientRepository;
import com.homework.Parking.repository.ReserveRepository;
import com.homework.Parking.repository.SpotParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final SpotParkingRepository spotParkingRepository;
    private final ClientRepository clientRepository;
    private final ReserveMapper reserveMapper;
    private final SpotParkingMapper spotMapper;

    public Reserve createReserve(Long clientId, Long spotId, LocalDateTime start, LocalDateTime end) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден."));
        SpotParking spot = spotParkingRepository.findById(spotId)
                .orElseThrow(() -> new SpotParkingException("Место не найдено"));

        if (spot.isBusy()) {
            throw new SpotParkingException("Место занято.");
        }
        if (reserveRepository.existsActiveReservationForSpot(spot, start, end)) {
            throw new SpotParkingException("Место уже зарезервировано на этот период");
        }
        if (start.isAfter(end) || start.isBefore(LocalDateTime.now())) {
            throw new SpotParkingException("Некорректные даты резервирования.");
        }

        Reserve reserve = Reserve.builder()
                .client(client)
                .spot(spot)
                .startReserve(start)
                .endReserve(end)
                .isActive(true)
                .build();

        return reserveRepository.save(reserve);
    }

    public ReserveResponse getReserveResponseById(Long reserveId) {
        Reserve reserve = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new SpotParkingException("Резерв не найден."));
        return reserveMapper.toResponse(reserve);
    }

    public List<ReserveResponse> getAllReserveIsActive() {
        return reserveRepository.findAll()
                .stream()
                .map(reserveMapper::toResponse)
                .toList();
    }

    public Reserve getReserveById(Long reserveId) {
        return reserveRepository.findById(reserveId)
                .orElseThrow(() -> new SpotParkingException("Резерв не найден."));
    }

    public List<SpotParkingResponse> getAllFreedomSpotForReserve(LocalDateTime start, LocalDateTime end) {
        return reserveRepository.findSpotsAvailableForReservation(start, end)
                .stream()
                .map(spotMapper::toResponse)
                .toList();
    }

    public void cancelReserve(Long reserveId) {
        Reserve reserve = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new SpotParkingException("Резерв не найден"));
        reserveRepository.delete(reserve);
    }

    public List<ReserveResponse> getActiveReserveByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));

        return reserveRepository.findByClientAndIsActiveTrue(client)
                .stream()
                .map(reserveMapper::toResponse)
                .toList();
    }

    @Scheduled(fixedRate = 60000)
    public void completeReserve() {
        List<Reserve> activeReserves = reserveRepository.findByIsActiveTrue();
        LocalDateTime now = LocalDateTime.now();

        for (Reserve reserve : activeReserves) {
            if (reserve.getEndReserve().isBefore(now)) {
                reserve.setIsActive(false);
                reserveRepository.save(reserve);
            }
        }
    }

    public ReservePriceResponse calculateReservePrice(Long spotId, LocalDateTime start, LocalDateTime end) {
        SpotParking spot = spotParkingRepository.findById(spotId)
                .orElseThrow(() -> new SpotParkingException("Место не найдено."));
        if (spot.isBusy()) {
            throw new SpotParkingException("Место занято");
        }

        long days = calculateDays(start, end);
        double startPricePerDays = (double) spot.getPrice().getPrice() / 30;

        Discount discountType = discountType(days);
        double totalPriceWithOutDiscount = startPricePerDays * days;
        double discountPrice = totalPriceWithOutDiscount * discountType.getDiscountRate();
        double finalPrice = totalPriceWithOutDiscount - discountPrice;

        return ReservePriceResponse.builder()
                .startPriceForDay(startPricePerDays)
                .totalDays(days)
                .totalDiscount(discountPrice)
                .discount(discountType)
                .discountAmount(discountPrice)
                .totalPrice(finalPrice)
                .build();
    }

    private long calculateDays(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toDays();
    }

    private Discount discountType(long days) {
        if (days >= Discount.Large_Discount.getMinDays()) {
            return Discount.Large_Discount;
        }
        if (days >= Discount.Medium_Discount.getMinDays()) {
            return Discount.Medium_Discount;
        }
        if (days >= Discount.Short_Discount.getMinDays()) {
            return Discount.Short_Discount;
        }
        return Discount.No_Discount;
    }
}
