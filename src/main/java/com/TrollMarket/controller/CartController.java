package com.TrollMarket.controller;

import com.TrollMarket.service.CartService;
import com.itextpdf.text.pdf.qrcode.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/index")
    public String index(@RequestParam String buyerUsername,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        var rows = cartService.dtoPage(buyerUsername, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "cart/cart-index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, @RequestParam String buyerUsername, RedirectAttributes redirectAttributes) {
        cartService.delete(id);
        redirectAttributes.addAttribute("buyerUsername", buyerUsername);
        return "redirect:/cart/index";
    }

    /////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/historyIndex")
    public String historyIndex(@RequestParam(required = false) String sellerUsername,
                               @RequestParam(required = false) String buyerUsername,
                               @RequestParam(defaultValue = "") Double minimum,
                               @RequestParam(defaultValue = "") Double maximum,
                               @RequestParam(defaultValue = "1") Integer page,
                               Model model) {
        var rows  = cartService.historyPage(sellerUsername, buyerUsername, minimum, maximum, page);
        var buyerDropdown = cartService.getBuyerDropdown();
        var sellerDropdown = cartService.getSellerDropdown();
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", maximum);
        model.addAttribute("selectedBuyerName", buyerUsername);
        model.addAttribute("selectedSellerName", sellerUsername);
        model.addAttribute("buyerDropdown", buyerDropdown);
        model.addAttribute("sellerDropdown", sellerDropdown);
        return "cart/admin-history";
    }

    @GetMapping("/purchaseAll")
    public String purchaseAll(@RequestParam String buyerUsername, @RequestParam(defaultValue = "1") Integer page, RedirectAttributes redirectAttributes, Model model) {
        var purchaseNotification = cartService.purchaseAll(buyerUsername);

        if (purchaseNotification == "") {
            redirectAttributes.addAttribute("buyerUsername", buyerUsername);
            return "redirect:/cart/index";
        } else if (purchaseNotification == "Your Cart Is Empty") {
            var rows = cartService.dtoPage(buyerUsername, page);
            model.addAttribute("grid", rows);
            model.addAttribute("totalPages", rows.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("emptyCart", purchaseNotification);
        }
        var rows = cartService.dtoPage(buyerUsername, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("purchaseNotification", purchaseNotification);
        return "cart/cart-index";
    }
}
