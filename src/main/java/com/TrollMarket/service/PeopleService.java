package com.TrollMarket.service;

import com.TrollMarket.dto.PeopleDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class PeopleService {

    public List<PeopleDTO> getPeople() {
        List<PeopleDTO> getPeople = new LinkedList<>();
        var lidya = new PeopleDTO();
        lidya.setNamaDepan("Lidya");
        lidya.setNamaBelakang("Susanti");
        lidya.setGender("Perempuan");
        lidya.setTanggalLahir(LocalDate.of(1999, 10, 10));
        lidya.setKotaLahir("Jakarta");

        var anto = new PeopleDTO();
        anto.setNamaDepan("Anto");
        anto.setNamaBelakang("Suherman");
        anto.setGender("Laki-laki");
        anto.setTanggalLahir(LocalDate.of(2001, 1, 6));
        anto.setKotaLahir("Semarang");

        var faisal = new PeopleDTO();
        faisal.setNamaDepan("Faisal");
        faisal.setNamaBelakang("Tamim");
        faisal.setGender("Laki-laki");
        faisal.setTanggalLahir(LocalDate.of(1991, 4, 8));
        faisal.setKotaLahir("Bandung");

        getPeople.add(lidya);
        getPeople.add(anto);
        getPeople.add(faisal);

        return getPeople;
    }
    public PeopleDTO getLidya () {
        var dto = new PeopleDTO();
        dto.setNamaDepan("Lidya");
        dto.setNamaBelakang("Susanti");
        dto.setGender("Perempuan");
        dto.setTanggalLahir(LocalDate.of(1999, 10, 10));
        dto.setKotaLahir("Jakarta");
        return dto;
    }

    public PeopleDTO getAnto () {
        var dto = new PeopleDTO();
        dto.setNamaDepan("Anto");
        dto.setNamaBelakang("Suherman");
        dto.setGender("Laki-laki");
        dto.setTanggalLahir(LocalDate.of(2001, 1, 6));
        dto.setKotaLahir("Semarang");
        return dto;
    }

    public PeopleDTO getFaisal () {
        var dto = new PeopleDTO();
        dto.setNamaDepan("Faisal");
        dto.setNamaBelakang("Tamim");
        dto.setGender("Laki-laki");
        dto.setTanggalLahir(LocalDate.of(1991, 4, 8));
        dto.setKotaLahir("Bandung");
        return dto;
    }
}
