package com.TrollMarket.controller;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.product.AddToCartDTO;
import com.TrollMarket.dto.product.UpsertMerchandiseDTO;
import com.TrollMarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/merchandiseIndex")
    public String index(@RequestParam String username,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model){
        var rows = productService.dtoPage(username, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "products/merchandise-index";
    }

    @GetMapping("/productInsertForm")
    public String insert(@RequestParam String username, Model model) {
        var dto = new UpsertMerchandiseDTO();
        var categoryDropdown = productService.getCategoryDropdown();
        dto.setUsername(username);
        model.addAttribute("dto", dto);
        model.addAttribute("categoryDropdown", categoryDropdown);
        return "/products/merchandise-form";
    }

    @GetMapping("/productUpdateForm")
    public String update(@RequestParam String username, @RequestParam Long id, Model model) {
        var dependencies = productService.countByCart(id);
        if (dependencies == 0) {
            var dto = productService.findOne(id);
            var categoryDropdown = productService.getCategoryDropdown();
            dto.setUsername(username);
            model.addAttribute("dto", dto);
            model.addAttribute("categoryDropdown", categoryDropdown);
            return "/products/merchandise-form";
        }
        model.addAttribute("dependencies", dependencies);
        return "products/merchandise-dependencies";
    }

    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("dto") UpsertMerchandiseDTO dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            productService.save(dto);
            redirectAttributes.addAttribute("username", dto.getUsername());
            return "redirect:/product/merchandiseIndex";
        }
        var categoryDropdown = productService.getCategoryDropdown();
        model.addAttribute("categoryDropdown", categoryDropdown);
        return "products/merchandise-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String username, @RequestParam Long id, RedirectAttributes redirectAttributes, Model model) {
        var dependencies = productService.countByCart(id);
        if (dependencies == 0) {
            productService.delete(id);
            redirectAttributes.addAttribute("username", username);
            return "redirect:/product/merchandiseIndex";
        }
        model.addAttribute("dependencies", dependencies);
        return "products/merchandise-dependencies";
    }

    @GetMapping("/setDiscontinue")
    public String setDiscontinue(@RequestParam Long id,
                                 @RequestParam String username,
                                 RedirectAttributes redirectAttributes) {
        productService.setDiscontinue(id);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/product/merchandiseIndex";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/shopIndex")
    public String index(@RequestParam(defaultValue = "") String productName,
                        @RequestParam(defaultValue = "") String category,
                        @RequestParam(defaultValue = "") String description,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        var rows = productService.shopPage(productName, category, description, page);
        var categoryDropdown = DropdownDTO.getCategoryDropdown();
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("productName", productName);
        model.addAttribute("categoryDropdown", categoryDropdown);
        model.addAttribute("description", description);
        model.addAttribute("shipmentDropdown",productService.getShipmentDropdown() );
        return "/products/shop-index";
    }

    @GetMapping("/addToCartForm")
    public String cartForm(@RequestParam Long productId, @RequestParam String buyerUsername, Model model) {
        var dto = new AddToCartDTO();
        var shipmentDropdown = productService.getShipmentDropdown();
        dto.setProductId(productId);
        dto.setBuyerUsername(buyerUsername);
        model.addAttribute("dto", dto);
        model.addAttribute("shipmentDropdown", shipmentDropdown);
        model.addAttribute("productId", productId);
        model.addAttribute("buyerUsername", buyerUsername);
        return "/products/add-to-cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(@Valid @ModelAttribute("dto") AddToCartDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            productService.addToCart(dto);
            return "redirect:/product/shopIndex";
        }
        model.addAttribute("shipmentDropdown", productService.getShipmentDropdown());
        return "/products/add-to-cart";
    }
}