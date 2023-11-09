package com.TrollMarket.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DataPembeliDTO {
    private String namaPembeli;
    private Integer jumlahQuantity;
    private String namaProduct;
}
