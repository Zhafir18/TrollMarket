package com.TrollMarket.controller;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.account.RegisterDTO;
import com.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        var dto = new RegisterDTO();
        model.addAttribute("dto", dto);
        model.addAttribute("roleDropdown", DropdownDTO.getRoleDropdown());
        return "account/register-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dto") RegisterDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            accountService.register(dto);
            return "redirect:/account/loginForm";
        }
        model.addAttribute("roleDropdown", DropdownDTO.getRoleDropdown());
        return "account/register-form";
    }

    @GetMapping("/registerFormAdmin")
    public String registerFormAdmin(Model model) {
        var dto = new RegisterDTO();
        dto.setRole("Admin");
        model.addAttribute("dto", dto);
        return "account/admin-form";
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin(@Valid @ModelAttribute("dto") RegisterDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            accountService.register(dto);
            return "redirect:/account/registerFormAdmin";
        }
        return "account/admin-form";
    }

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "/account/login-form";
    }

    @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(Model model) {
        return "/account/access-denied";
    }

    @GetMapping("/loginFailed")
    public String loginFailed(Model model) {
        return "/account/login-failed";
    }

    @GetMapping("/userProfile")
    public String userProfile(@RequestParam String username,
                              @RequestParam(defaultValue = "1") Integer page,
                              Model model) {
        var header = accountService.getUserProfile(username);
        var rows = accountService.getUserHistory(username, page);
        model.addAttribute("header", header);
        model.addAttribute("username", username);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "user/user-profile";
    }
}
