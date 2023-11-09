package com.TrollMarket.dto.shipment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ShipmentRowDTO {
    private Long id;
    private String name;
    private Double price;
    private Boolean isService;
    private String status;

    public ShipmentRowDTO(Long id, String name, Double price, Boolean isService) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isService = isService;
    }
}
