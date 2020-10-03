package com.owpk.hw.services;

import com.owpk.hw.entities.Order;
import com.owpk.hw.repositories.OrderRepo;
import com.owpk.hw.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
  private OrderRepo orderRepo;

  public List<Order> getAllOrdersByCustomerId(Long id) {
    return orderRepo.findAllByCustomerId(id);
  }

  public List<Order> findAll() {
    return orderRepo.findAll();
  }

  public void createOrder(Order order) {
    orderRepo.save(order);
  }
}
