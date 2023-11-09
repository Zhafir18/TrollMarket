package com.TrollMarket.service;

import com.TrollMarket.dto.orders.DataPembeliDTO;
import com.TrollMarket.dto.orders.MenuTigaDTO;
import com.TrollMarket.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class SoalZaimService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<DataPembeliDTO> dataPembeli() {
        var rows = ordersRepository.getData();
        return rows;
    }

    public Integer transactionPaid() {
        var data = ordersRepository.getTotalQuantityPaid();
        return data;
    }

    public MenuTigaDTO getMenuTiga() {
        var data = new MenuTigaDTO();
        data.setNamaSaya("Zhafir");
        data.setTrainerSaya("Boby");
        return data;
    }
}
