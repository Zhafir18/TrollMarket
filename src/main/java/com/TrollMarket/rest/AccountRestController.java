package com.TrollMarket.rest;

import com.TrollMarket.dto.account.TopUpDTO;
import com.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountRestController extends AbstractRestController{
    @Autowired
    private AccountService accountService;

    @PostMapping("/topup")
    public ResponseEntity<Object> topupForm(@Valid @RequestBody TopUpDTO topup, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            accountService.topUp(topup);
            return ResponseEntity.status(200).body("Berhasil Topup");
        }
        return ResponseEntity.status(422).body(getError(bindingResult.getAllErrors()));
    }
}
