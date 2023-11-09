package com.TrollMarket.repository;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.product.MerchandiseRowDTO;
import com.TrollMarket.dto.product.ShopRowDTO;
import com.TrollMarket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT new com.TrollMarket.dto.product.MerchandiseRowDTO(
                pro.id,
                pro.name,
                pro.category,
                pro.isDiscontinue
            )
            FROM Product AS pro
            JOIN pro.account AS acc
            WHERE acc.username = :username
            """)

    public Page<MerchandiseRowDTO> getRow(@Param("username") String username, Pageable pageable);

    @Query("""
            SELECT new com.TrollMarket.dto.product.ShopRowDTO(
                pro.id,
                pro.name,
                pro.price
            )
            FROM Product AS pro
            JOIN pro.account AS acc
            WHERE pro.isDiscontinue = FALSE
            AND (:productName IS NULL OR pro.name LIKE %:productName%)
            AND (:category IS NULL OR pro.category LIKE %:category%)
            AND (:description IS NULL OR pro.description LIKE %:description%)
            """)
    public Page<ShopRowDTO> getShopRow(@Param("productName") String productName,
                                       @Param("category") String category,
                                       @Param("description") String description,
                                       Pageable pageable);
}
