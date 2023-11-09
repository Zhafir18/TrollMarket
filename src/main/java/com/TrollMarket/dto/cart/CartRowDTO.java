package com.TrollMarket.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CartRowDTO {
    private Long id;
    private String product;
    private Integer quantity;
    private String shipment;
    private String seller;
    private Double totalPrice;
}
