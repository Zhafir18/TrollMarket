package com.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Product")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Category")
    private String category;

    @Column(name = "Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "SellerUsername", insertable = false, updatable = false)
    private Account account;

    @Column(name = "SellerUsername")
    private String sellerUsername;

    @Column(name = "IsDiscontinue")
    private Boolean isDiscontinue;

    @Column(name = "Price")
    private Double price;
}
