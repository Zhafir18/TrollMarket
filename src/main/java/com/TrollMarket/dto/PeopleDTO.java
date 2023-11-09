package com.TrollMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PeopleDTO {
    private String namaDepan;
    private String namaBelakang;
    private String gender;
    private LocalDate tanggalLahir;
    private String kotaLahir;
}
