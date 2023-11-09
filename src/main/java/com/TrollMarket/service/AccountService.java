package com.TrollMarket.service;

import com.TrollMarket.dto.account.RegisterDTO;
import com.TrollMarket.dto.account.TopUpDTO;
import com.TrollMarket.dto.account.UserProfileDTO;
import com.TrollMarket.dto.orders.UserOrderHistoryDTO;
import com.TrollMarket.entity.Account;
import com.TrollMarket.repository.AccountRepository;
import com.TrollMarket.repository.OrdersRepository;
import com.TrollMarket.utility.ApplicationUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void  register(RegisterDTO dto) {
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashedPassword);
        entity.setRole(dto.getRole());
        entity.setAddress(dto.getAddress());
        entity.setName(dto.getName());
        entity.setBalance(0d);
        accountRepository.save(entity);
    }

    public Boolean isUsernameExist(String username) {
        var user = accountRepository.countExistingUser(username);
        return user > 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account= accountRepository.findById(username).get();
        if(account == null){
            throw  new UsernameNotFoundException("User not found");
        } else {
            var userDetail= new ApplicationUserDetail(account);
            return userDetail;
        }
    }

    public String getRole(String username) {
        return accountRepository.findById(username).get().getRole();
    }

    public UserProfileDTO getUserProfile(String username) {
        var user = accountRepository.findById(username).get();
        var header = new UserProfileDTO();
        header.setUsername(username);
        header.setName(user.getName());
        header.setRole(user.getRole());
        header.setAddress(user.getAddress());
        header.setBalance(user.getBalance());
        return header;
    }

    public Page<UserOrderHistoryDTO> getUserHistory(String username, Integer page) {
        var pageable = PageRequest.of(page - 1, 10);
        var rows = ordersRepository.getUserOrderHistory(username, pageable);
        return rows;
    }

    public void topUp(TopUpDTO topUpDTO) {
        var dto = accountRepository.findById(topUpDTO.getUsername()).get();
        dto.setBalance(dto.getBalance() + topUpDTO.getTopup());
        accountRepository.save(dto);
    }
}
