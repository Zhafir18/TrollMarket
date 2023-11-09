package com.TrollMarket.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderHistoryDTO {
    private LocalDate orderDate;
    private String sellerName;
    private String buyerName;
    private String productName;
    private Integer quantity;
    private String shipmentName;
    private Double totalPrice;
}
