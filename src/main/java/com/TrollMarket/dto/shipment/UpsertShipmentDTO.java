package com.TrollMarket.dto.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpsertShipmentDTO {
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String name;

    @NotNull(message = "Harga tidak boleh kosong")
    private Double price;

    private Boolean isService;
}
