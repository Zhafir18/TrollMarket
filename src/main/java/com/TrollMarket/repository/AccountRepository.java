package com.TrollMarket.repository;

import com.TrollMarket.dto.DropdownDTO;
import com.TrollMarket.dto.account.UserProfileDTO;
import com.TrollMarket.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("""
            SELECT COUNT(acc.username)
            FROM Account AS acc
            WHERE acc.username = :username
            """)

    public Long countExistingUser(@Param("username") String username);

    @Query("""
            SELECT new com.TrollMarket.dto.account.UserProfileDTO(
                acc.username,
                acc.name,
                acc.role,
                acc.address,
                acc.balance
            )
            FROM Account AS acc
            WHERE acc.username = :username
            """)

    public UserProfileDTO getUserProfile(@Param("username") String username);

    @Query("""
            SELECT new com.TrollMarket.dto.DropdownDTO(
                ac.username,
                ac.name
            )
            FROM Account AS ac
            WHERE ac.role = 'Buyer' AND ac.name IS NOT NULL
            """)

    public List<DropdownDTO> getBuyerDropdown();

    @Query("""
            SELECT new com.TrollMarket.dto.DropdownDTO(
                ac.username,
                ac.name
            )
            FROM Account AS ac
            WHERE ac.role = 'Seller'
            """)

    public List<DropdownDTO> getSellerDropdown();
}
