package com.TrollMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class    DropdownDTO {
    private Object value;
    private String textContent;

    public static List<DropdownDTO> getRoleDropdown() {
        var dropdownDTO = new ArrayList<DropdownDTO>();
        dropdownDTO.add(new DropdownDTO("Seller", "Seller"));
        dropdownDTO.add(new DropdownDTO("Buyer", "Buyer"));
        return dropdownDTO;
    }

    public static List<DropdownDTO> getCategoryDropdown() {
        var dropdownDTO = new ArrayList<DropdownDTO>();
        dropdownDTO.add(new DropdownDTO("Elektronik", "Elektronik"));
        dropdownDTO.add(new DropdownDTO("Kesehatan", "Kesehatan"));
        dropdownDTO.add(new DropdownDTO("Olahraga", "Olahraga"));
        dropdownDTO.add(new DropdownDTO("Otomotif", "Otomotif"));
        dropdownDTO.add(new DropdownDTO("Produk lainnya", "Produk lainnya"));
        return dropdownDTO;
    }
}
