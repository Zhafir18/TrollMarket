package com.TrollMarket.rest;

import com.TrollMarket.dto.product.AddToCartDTO;
import com.TrollMarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductRestController extends AbstractRestController{

    @Autowired
    private ProductService productService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getProductDetail(@PathVariable Long id) {
        var dto = productService.getDetail(id);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/merchDetail/{id}")
    public ResponseEntity<Object> getMerchDetail(@PathVariable Long id) {
        var dto = productService.getMerchandiseDetail(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToCart(@Valid @RequestBody AddToCartDTO dto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            var response = productService.addToCart(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getError(bindingResult.getAllErrors()));
    }
}
