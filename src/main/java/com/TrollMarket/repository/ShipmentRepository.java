package com.TrollMarket.repository;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.shipment.ShipmentRowDTO;
import com.TrollMarket.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query("""
            SELECT new com.TrollMarket.dto.shipment.ShipmentRowDTO(
                shi.id,
                shi.name,
                shi.price,
                shi.isService
            )
            FROM Shipment AS shi
            """)
    public Page<ShipmentRowDTO> getRow(Pageable pageable);

    @Query("""
            SELECT new com.TrollMarket.dto.DropdownDTO(
                shi.Id,
                shi.name
            )
            FROM Shipment AS shi
            WHERE shi.isService = TRUE
            """)

    public List<DropdownDTO> getShipmentDropdown();
}
