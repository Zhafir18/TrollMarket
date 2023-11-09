package com.TrollMarket.validation;

import com.TrollMarket.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !accountService.isUsernameExist(value);
    }
}
