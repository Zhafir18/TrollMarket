package com.TrollMarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MVCSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests((request) -> request
                .requestMatchers("/resources/**", "/account/**", "/home/**")
                    .permitAll()
                .requestMatchers("/shipment/**", "/account/registerFormAdmin", "/account/registerAdmin", "/cart/historyIndex").hasAuthority("Admin")
                .requestMatchers("/account/userProfile/**").hasAnyAuthority("Buyer", "Seller")
                .requestMatchers("/product/merchandiseIndex", "/product/upsertForm", "/product/upsert", "/product/delete", "/product/setDiscontinue").hasAuthority("Seller")
                .requestMatchers("/product/shopIndex", "/product/addToCartForm", "/product/addToCart", "/cart/index", "/cart/delete", "/cart/purchaseAll").hasAuthority("Buyer")
                .anyRequest().authenticated()
        ).formLogin((form) -> form
                .loginPage("/account/loginForm")
                .loginProcessingUrl("/login")
                .failureUrl("/account/loginFailed")
        ).logout((logout) -> logout
                .logoutUrl("/logout")
        ).exceptionHandling((exception) -> exception
                .accessDeniedPage("/account/accessDenied")
        );
        return httpSecurity.build();
    }
}
