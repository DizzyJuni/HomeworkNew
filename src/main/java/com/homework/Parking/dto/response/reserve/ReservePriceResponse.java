package com.homework.Parking.dto.response.reserve;

import com.homework.Parking.enums.Discount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservePriceResponse {
    private Double startPriceForDay;
    private Long totalDays;
    private Double totalDiscount;
    private Discount discount;
    private Double discountAmount;
    private Double totalPrice;
}
