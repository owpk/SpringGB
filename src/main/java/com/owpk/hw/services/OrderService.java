package com.owpk.hw.services;

import com.owpk.hw.entities.Order;
import com.owpk.hw.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
  private OrderRepo orderRepo;

  public List<Order> findAll() {
    return orderRepo.findAll();
  }

  public List<Order> findByCustomerId(Long customerId) {
    return orderRepo.findAllByCustomerId(customerId);
  }
}
