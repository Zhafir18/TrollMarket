package com.TrollMarket.rest;

import com.TrollMarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tes")
public class TesNiaRestController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Object> getData() {
        var data = cartService.dtos();
        return ResponseEntity.status(200).body(data);
    }
}
