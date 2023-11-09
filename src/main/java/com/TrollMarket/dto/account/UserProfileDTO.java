package com.TrollMarket.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserProfileDTO {
    private String username;
    private String name;
    private String role;
    private String address;
    private Double balance;
}
