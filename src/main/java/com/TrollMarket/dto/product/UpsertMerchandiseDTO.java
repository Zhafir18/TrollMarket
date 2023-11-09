package com.TrollMarket.dto.product;

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

public class UpsertMerchandiseDTO {
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong!")
    @Size(max = 200, message = "Maksimal 200 karakter")
    private String name;

    @NotBlank(message = "Category tidak boleh kosong!")
    @Size(max = 100, message = "Maksimal 100 karakter")
    private String category;

    @Size(max = 500, message = "Maksimal 500 karakter")
    private String description;

    @NotNull(message = "Price tidak boleh kosong")
    private Double price;

    private Boolean isDiscontinue;

    private String username;
}
