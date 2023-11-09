package com.TrollMarket.dto.tesRama;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProfileDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<CompetenciesDTO> competencies = new LinkedList<>();
}
