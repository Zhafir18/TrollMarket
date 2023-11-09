package com.TrollMarket.rest;

import com.TrollMarket.dto.shipment.UpsertShipmentDTO;
import com.TrollMarket.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentRestController extends AbstractRestController{

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> upsertForm(@PathVariable(required = false) Long id) {
        var dto = shipmentService.findOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody UpsertShipmentDTO dto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            var response = shipmentService.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getError(bindingResult.getAllErrors()));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody UpsertShipmentDTO dto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            var response = shipmentService.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getError(bindingResult.getAllErrors()));
    }

    @GetMapping("/failed/{id}")
    public ResponseEntity<Object> shipmentDependencies(@PathVariable Long id) {
        var dependencies = shipmentService.countCartByShipment(id);
        if (dependencies == 0) {
            return ResponseEntity.status(200).body("");
        }
        return ResponseEntity.status(400).body("Tidak dapat update shipment karena memiliki" + " " + dependencies + " " + "terhadap cart");
    }
}
