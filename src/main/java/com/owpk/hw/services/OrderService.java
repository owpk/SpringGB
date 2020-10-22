package com.owpk.hw.services;

import com.owpk.hw.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepo orderRepo;
}
