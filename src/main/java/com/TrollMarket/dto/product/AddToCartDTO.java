package com.TrollMarket.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddToCartDTO {
    private Long cartId;
    private Long productId;
    private String buyerUsername;

    @NotNull(message = "Harus memilih shipment")
    private Long shipmentId;

    @NotNull(message = "Harus memilih quantity")
    @Min(value = 1, message = "Minimal input 1")
    private Integer quantity;
}
