package com.owpk.hw.controllers;

import com.owpk.hw.entities.Order;
import com.owpk.hw.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

  private OrderService orderService;

  @GetMapping
  private String getOrders(Model model) {
    List<Order> orders = orderService.findAll();
    model.addAttribute("orders", orders);
    return "order";
  }

}
