package com.TrollMarket.service;

import com.TrollMarket.dto.tesRama.CompetenciesDTO;
import com.TrollMarket.dto.tesRama.ProfileDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TesRamaService {

    public List<ProfileDTO> getProfile() {
        List<ProfileDTO> dto = new LinkedList<>();

        var edward = new ProfileDTO();
        edward.setFirstName("Edward");
        edward.setLastName("Brenz");
        edward.setAge(34);

        var grace = new ProfileDTO();
        grace.setFirstName("Grace");
        grace.setLastName("Suhendra");
        grace.setAge(25);

        List<CompetenciesDTO> edwardCompetencies = new LinkedList<>();

        edwardCompetencies.add(new CompetenciesDTO("SQL", 80));
        edwardCompetencies.add(new CompetenciesDTO("Java", 70));
        edwardCompetencies.add(new CompetenciesDTO("JavaScript", 50));

        List<CompetenciesDTO> graceCompetencies = new LinkedList<>();

        graceCompetencies.add(new CompetenciesDTO("HTML", 90));
        graceCompetencies.add(new CompetenciesDTO("Java", 80));

        edward.setCompetencies(edwardCompetencies);
        dto.add(edward);

        grace.setCompetencies(graceCompetencies);
        dto.add(grace);

        return dto;
    }
}
