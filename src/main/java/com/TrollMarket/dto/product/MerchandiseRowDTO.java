package com.TrollMarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MerchandiseRowDTO {
    private Long id;
    private String name;
    private String category;
    private Boolean isDiscontinue;
    private String status;

    public MerchandiseRowDTO(Long id, String name, String category, Boolean isDiscontinue) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isDiscontinue = isDiscontinue;
    }
}
