package com.TrollMarket.service;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.product.*;
import com.TrollMarket.entity.Cart;
import com.TrollMarket.entity.Product;
import com.TrollMarket.repository.AccountRepository;
import com.TrollMarket.repository.CartRepository;
import com.TrollMarket.repository.ProductRepository;
import com.TrollMarket.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public String isDiscontinue(MerchandiseRowDTO dto){
        String status;
        if (!dto.getIsDiscontinue()) {
            status = "No";
        }
        else {
            status = "Yes";
        }
        return status;
    }

    public Page<MerchandiseRowDTO> dtoPage(@RequestParam String username, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var rows = productRepository.getRow(username, pageable);

        for (MerchandiseRowDTO dto : rows) {
            dto.setStatus(isDiscontinue(dto));
        }

        return rows;
    }

    public UpsertMerchandiseDTO save(UpsertMerchandiseDTO dto) {
        var merchandise = new Product();
        merchandise.setId(dto.getId());
        merchandise.setName(dto.getName());
        merchandise.setCategory(dto.getCategory());
        merchandise.setDescription(dto.getDescription());
        merchandise.setPrice(dto.getPrice());
        merchandise.setIsDiscontinue(dto.getIsDiscontinue());
        merchandise.setSellerUsername(dto.getUsername());
        var responseEntity = productRepository.save(merchandise);
        var responseDTO = new UpsertMerchandiseDTO(
                responseEntity.getId(),
                responseEntity.getName(),
                responseEntity.getCategory(),
                responseEntity.getDescription(),
                responseEntity.getPrice(),
                responseEntity.getIsDiscontinue(),
                responseEntity.getSellerUsername()
        );
        return responseDTO;
    }

    public UpsertMerchandiseDTO findOne(Long id) {
        var merchandise = productRepository.findById(id).get();
        var dto = new UpsertMerchandiseDTO(
                merchandise.getId(),
                merchandise.getName(),
                merchandise.getCategory(),
                merchandise.getDescription(),
                merchandise.getPrice(),
                merchandise.getIsDiscontinue(),
                merchandise.getSellerUsername()
        );
        return dto;
    }

    public MerchandiseDetailDTO getMerchandiseDetail(Long id) {
        var product = productRepository.findById(id).get();
        var dto = new MerchandiseDetailDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getIsDiscontinue()
        );
        return dto;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public void setDiscontinue(Long id) {
        var product = productRepository.findById(id).get();
        product.setIsDiscontinue(true);
        productRepository.save(product);
    }

    /////////////////////////////////////////////////////////////////

    public Page<ShopRowDTO> shopPage(String productName, String category, String description, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var rows = productRepository.getShopRow(productName, category, description, pageable);
        return rows;
    }

    public ShopDetailDTO getDetail(Long id) {
        var product = productRepository.findById(id).get();
        var seller = accountRepository.findById(product.getSellerUsername()).get();
        var dto = new ShopDetailDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                seller.getName()
        );
        return dto;
    }

    public List<DropdownDTO> getShipmentDropdown() {
        return shipmentRepository.getShipmentDropdown();
    }

    public List<DropdownDTO> getCategoryDropdown() {
        return DropdownDTO.getCategoryDropdown();
    }

    public AddToCartDTO addToCart(AddToCartDTO dto) {
        var cart = new Cart();
        cart.setProductId(dto.getProductId());
        cart.setBuyerUsername(dto.getBuyerUsername());
        cart.setShipmentId(dto.getShipmentId());
        cart.setQuantity(dto.getQuantity());
        var responseEntity = cartRepository.save(cart);
        var responseDTO = new AddToCartDTO(
                responseEntity.getId(),
                responseEntity.getProductId(),
                responseEntity.getBuyerUsername(),
                responseEntity.getShipmentId(),
                responseEntity.getQuantity()
        );
        return responseDTO;
    }

    public Integer countByCart(@RequestParam Long id) {
        return cartRepository.countByMerchandise(id);
    }
}
