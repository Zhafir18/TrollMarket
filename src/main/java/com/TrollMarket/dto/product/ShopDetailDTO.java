package com.TrollMarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ShopDetailDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private Double price;
    private String sellerName;
}
