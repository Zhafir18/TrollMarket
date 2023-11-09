package com.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "Cart")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BuyerUsername", insertable = false, updatable = false)
    private Account account;

    @Column(name = "BuyerUsername")
    private String buyerUsername;

    @ManyToOne
    @JoinColumn(name = "ProductId", insertable = false, updatable = false)
    private Product product;

    @Column(name = "ProductId")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "ShipmentId", insertable = false, updatable = false)
    private Shipment shipment;

    @Column(name = "ShipmentId")
    private Long shipmentId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "OrderDate")
    private LocalDate orderDate;
}
