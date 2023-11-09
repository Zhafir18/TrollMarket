package com.TrollMarket.repository;

import com.TrollMarket.dto.cart.CartListDTO;
import com.TrollMarket.dto.cart.CartRowDTO;
import com.TrollMarket.dto.orders.UserOrderHistoryDTO;
import com.TrollMarket.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("""
            SELECT new com.TrollMarket.dto.cart.CartRowDTO(
                car.id,
                pro.name,
                car.quantity,
                shi.name,
                acc.name,
                ((car.quantity * pro.price) + shi.price)
            )
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            JOIN pro.account AS acc
            WHERE car.buyerUsername = :buyerUsername AND car.orderDate IS NULL
            """)

    public Page<CartRowDTO> getCartRow(@RequestParam("buyerUsername") String buyerUsername, Pageable pageable);

    @Query("""
            SELECT new com.TrollMarket.dto.cart.CartListDTO(
                car.id,
                pro.id,
                shi.id,
                car.buyerUsername,
                car.quantity,
                car.orderDate,
                acc.username
            )
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            JOIN pro.account AS acc
            WHERE car.buyerUsername = :buyerUsername AND car.orderDate IS NULL
            """)

    public List<CartListDTO> getUserCart(@RequestParam("buyerUsername") String buyerUsername);

    @Query("""
            SELECT SUM((car.quantity * pro.price) + shi.price)
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            WHERE car.buyerUsername = :buyerUsername AND car.orderDate IS NULL
            """)

    public Double getTotalPrice(@RequestParam("buyerUsername") String buyerUsername);

    @Query("""
            SELECT COUNT(car.shipmentId)
            FROM Cart AS car
            WHERE car.shipmentId = :shipmentId
            """)

    public Integer countByShipment(@RequestParam("shipmentId") Long shipmentId);

    @Query("""
            SELECT COUNT(car.productId)
            FROM Cart AS car
            WHERE car.productId = :merchandiseId
            """)

    public Integer countByMerchandise(@RequestParam("merchandiseId") Long merchandiseId);
}
