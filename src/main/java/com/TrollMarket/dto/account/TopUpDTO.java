package com.TrollMarket.dto.account;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TopUpDTO {
    private String username;

    @Min(value = 20000, message = "Minimal top up 20.000")
    @Digits(integer=20, fraction=2, message="Must be a decimal value with 2 decimal points.")
    @NotNull(message = "Harus memasukkan jumlah topup")
    private Double topup;
}
