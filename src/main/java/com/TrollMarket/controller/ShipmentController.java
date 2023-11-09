package com.TrollMarket.controller;

import com.TrollMarket.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/index")
    public String shipmentRow(@RequestParam(defaultValue = "1") Integer page, Model model) {
        var rows = shipmentService.dtoPage(page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "shipment/shipment-index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        var dependencies = shipmentService.countCartByShipment(id);
        if (dependencies == 0) {
            shipmentService.delete(id);
            return "redirect:/shipment/index";
        }
            model.addAttribute("dependencies", dependencies);
            return "shipment/shipment-delete";
    }

    @GetMapping("/setService")
    public String setService(@RequestParam Long id) {
        shipmentService.setService(id);
        return "redirect:/shipment/index";
    }
}
