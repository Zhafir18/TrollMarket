package com.TrollMarket.service;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.cart.CartListDTO;
import com.TrollMarket.dto.cart.CartRowDTO;
import com.TrollMarket.dto.orders.OrderHistoryDTO;
import com.TrollMarket.dto.orders.TesNiaDTO;
import com.TrollMarket.dto.orders.UserOrderHistoryDTO;
import com.TrollMarket.entity.Cart;
import com.TrollMarket.repository.AccountRepository;
import com.TrollMarket.repository.CartRepository;
import com.TrollMarket.repository.OrdersRepository;
import com.TrollMarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<CartRowDTO> dtoPage(String buyerUsername, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var rows = cartRepository.getCartRow(buyerUsername, pageable);
        return rows;
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    public List<DropdownDTO> getBuyerDropdown() {
        return accountRepository.getBuyerDropdown();
    }

    public List<DropdownDTO> getSellerDropdown() {
        return accountRepository.getSellerDropdown();
    }

    public Page<OrderHistoryDTO> historyPage(String sellerUsername, String buyerUsername, Double minimum, Double maximum, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var rows = ordersRepository.getHistory(sellerUsername, buyerUsername, minimum, maximum, pageable);
        return rows;
    }

    public String purchaseAll(String buyerUsername) {
        var cartList = cartRepository.getUserCart(buyerUsername);
        var orderDate = LocalDate.now();
        var buyer = accountRepository.findById(buyerUsername).get();
        var totalPrice = cartRepository.getTotalPrice(buyerUsername);

        if (totalPrice == null) {
            return "Your Cart Is Empty";
        }

        if (buyer.getBalance() > totalPrice) {
            for (CartListDTO dto : cartList) {
                dto.setOrderDate(orderDate);
                var entity = new Cart();
                entity.setId(dto.getCartId());
                entity.setProductId(dto.getProductId());
                entity.setBuyerUsername(dto.getBuyerUsername());
                entity.setShipmentId(dto.getShipmentId());
                entity.setQuantity(dto.getQuantity());
                entity.setOrderDate(dto.getOrderDate());
                cartRepository.save(entity);

                var seller = accountRepository.findById(dto.getSellerUsername()).get();
                var product = productRepository.findById(dto.getProductId()).get();
                seller.setBalance(seller.getBalance() + (product.getPrice() * dto.getQuantity()));
                accountRepository.save(seller);
            }
            buyer.setBalance(buyer.getBalance() - totalPrice);
            accountRepository.save(buyer);
            return "";
        }
        return "Total harga melebihi balance yang ada, silahkan topup balance terlebih dahulu";
    }

    public List<TesNiaDTO> dtos() {
        var data = ordersRepository.getDTO();
        return data;
    }
}
