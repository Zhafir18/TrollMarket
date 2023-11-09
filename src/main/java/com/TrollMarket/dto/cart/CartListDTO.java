package com.TrollMarket.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CartListDTO {
    private Long cartId;
    private Long productId;
    private Long shipmentId;
    private String buyerUsername;
    private Integer quantity;
    private LocalDate orderDate;
    private String sellerUsername;
}
