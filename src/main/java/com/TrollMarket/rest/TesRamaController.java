package com.TrollMarket.rest;

import com.TrollMarket.service.TesRamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tesRama")
public class TesRamaController {

    @Autowired
    private TesRamaService tesRamaService;

    @GetMapping
    public ResponseEntity<Object> getProfile() {
        var people = tesRamaService.getProfile();
        return ResponseEntity.status(200).body(people);
    }
}
