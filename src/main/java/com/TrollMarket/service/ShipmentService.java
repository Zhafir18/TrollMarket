package com.TrollMarket.service;

import com.TrollMarket.dto.shipment.ShipmentRowDTO;
import com.TrollMarket.dto.shipment.UpsertShipmentDTO;
import com.TrollMarket.entity.Shipment;
import com.TrollMarket.repository.CartRepository;
import com.TrollMarket.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private CartRepository cartRepository;

    public String getService(ShipmentRowDTO dto) {
        String status;
        if (!dto.getIsService()) {
            status = "No";
        }
        else {
            status = "Yes";
        }
        return status;
    }

    public Page<ShipmentRowDTO> dtoPage(Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var rows = shipmentRepository.getRow(pageable);

        for (ShipmentRowDTO dto : rows) {
            dto.setStatus(getService(dto));
        }

        return rows;
    }

    public UpsertShipmentDTO save(UpsertShipmentDTO dto) {
        var shipment = new Shipment();
        shipment.setId(dto.getId());
        shipment.setName(dto.getName());
        shipment.setPrice(dto.getPrice());
        shipment.setIsService(dto.getIsService());
        var responseEntity = shipmentRepository.save(shipment);
        var responseDTO = new UpsertShipmentDTO(
                responseEntity.getId(),
                responseEntity.getName(),
                responseEntity.getPrice(),
                responseEntity.getIsService()
        );
        return responseDTO;
    }

    public UpsertShipmentDTO findOne(Long id) {
        var shipment = shipmentRepository.findById(id).get();
        var dto = new UpsertShipmentDTO(
                shipment.getId(),
                shipment.getName(),
                shipment.getPrice(),
                shipment.getIsService()
        );
        return dto;
    }

    public void delete(Long id) {
        shipmentRepository.deleteById(id);
    }

    public void setService(Long id) {
        var shipment = shipmentRepository.findById(id).get();
        shipment.setIsService(false);
        shipmentRepository.save(shipment);
    }

    public Integer countCartByShipment(Long id) {
        return cartRepository.countByShipment(id);
    }
}
