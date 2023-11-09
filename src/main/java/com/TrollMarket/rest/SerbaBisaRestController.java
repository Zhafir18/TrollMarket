package com.TrollMarket.rest;

import com.TrollMarket.service.SoalZaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/serbabisa")
public class SerbaBisaRestController {

    @Autowired
    private SoalZaimService soalZaimService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> serbaBisa(@PathVariable Long id) {
        if (id == 1) {
            var dto = soalZaimService.dataPembeli();
            return ResponseEntity.status(200).body(dto);
        } else if (id == 2) {
            var data2 = soalZaimService.transactionPaid();
            return ResponseEntity.status(200).body(data2);
        } else if (id == 3) {
            var data3 = soalZaimService.getMenuTiga();
            return ResponseEntity.status(200).body(data3);
        }
        return ResponseEntity.status(200).body("Tidak dapat menerima input");
    }
}
