package com.TrollMarket.dto.account;

import com.TrollMarket.validation.ComparePassword;
import com.TrollMarket.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ComparePassword(message = "Password tidak sama")

public class RegisterDTO {
    @UniqueUsername(message = "Username sudah digunakan")
    @NotBlank(message = "Username wajib diisi")
    @Size(max = 100, message = "Maksimal 100 Karakter")
    private String username;

    @NotBlank(message = "Password wajib diisi")
    @Size(min = 5, message = "Minimal 5 Karakter")
    private String password;

    @NotBlank(message = "Confirm Password wajib diisi")
    @Size(min = 5, message = "Minimal 5 karakter")
    private String confirmPassword;

    @NotBlank(message = "Role wajib diisi")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String role;

    @Size(max = 100, message = "Maksimal 100 karakter")
    private String name;

    @Size(max = 100, message = "Maksimal 100 karakter")
    private String address;

    private Double balance;
}
