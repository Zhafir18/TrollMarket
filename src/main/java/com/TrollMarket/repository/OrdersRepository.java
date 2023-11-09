package com.TrollMarket.repository;

import com.TrollMarket.dto.orders.DataPembeliDTO;
import com.TrollMarket.dto.orders.OrderHistoryDTO;
import com.TrollMarket.dto.orders.TesNiaDTO;
import com.TrollMarket.dto.orders.UserOrderHistoryDTO;
import com.TrollMarket.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Cart, Long> {

    @Query("""
            SELECT new com.TrollMarket.dto.orders.UserOrderHistoryDTO(
                pro.name,
                car.quantity,
                shi.name,
                ((car.quantity * pro.price) + shi.price),
                car.orderDate)
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.shipment AS shi
            JOIN car.account AS acc
            WHERE (acc.username = :username OR pro.sellerUsername = :username) AND car.orderDate IS NOT NULL
            """)

    public Page<UserOrderHistoryDTO> getUserOrderHistory(@RequestParam("username") String username, Pageable pageable);

    @Query("""
            SELECT new com.TrollMarket.dto.orders.OrderHistoryDTO(
                car.orderDate,
                sel.name,
                buy.name,
                pro.name,
                car.quantity,
                shi.name,
                ((car.quantity * pro.price) + shi.price)
            )
            FROM Cart AS car
            JOIN car.product AS pro
            JOIN car.account AS buy
            JOIN car.shipment AS shi
            JOIN pro.account AS sel
            WHERE car.orderDate IS NOT NULL
            AND (:sellerUsername IS NULL OR sel.username LIKE %:sellerUsername%) 
            AND (:buyerUsername IS NULL OR buy.username LIKE %:buyerUsername%)
            AND (:minimum IS NULL OR ((car.quantity * pro.price) + shi.price) > :minimum)
            AND (:maximum IS NULL OR ((car.quantity * pro.price) + shi.price) < :maximum )
            """)

    public Page<OrderHistoryDTO> getHistory(@Param("sellerUsername") String sellerUsername,
                                            @Param("buyerUsername") String buyerUsername,
                                            @Param("minimum") Double minimum,
                                            @Param("maximum") Double maximum,
                                            Pageable pageable);

    @Query("""
            SELECT new com.TrollMarket.dto.orders.DataPembeliDTO(
                acc.name,
                car.quantity,
                pro.name
            )
            FROM Cart AS car
            JOIN car.account AS acc
            JOIN car.product AS pro
            WHERE car.orderDate IS NULL
            """)
    public List<DataPembeliDTO> getData();

    @Query("""
            SELECT COUNT(car.id)
            FROM Cart AS car
            WHERE car.orderDate IS NOT NULL
            """)
    public Integer getTotalQuantityPaid();

    @Query("""
            SELECT new com.TrollMarket.dto.orders.TesNiaDTO(
                car.buyerUsername,
                SUM(car.quantity)
            )
            FROM Cart AS car
            WHERE car.orderDate IS NULL
            GROUP BY car.buyerUsername
            """)
    public List<TesNiaDTO> getDTO();
}
