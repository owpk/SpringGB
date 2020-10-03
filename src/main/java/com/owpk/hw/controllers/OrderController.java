package com.owpk.hw.controllers;

import com.owpk.hw.entities.Customer;
import com.owpk.hw.entities.Order;
import com.owpk.hw.services.CustomerService;
import com.owpk.hw.services.OrderService;
import com.owpk.hw.utils.Cart;
import com.owpk.hw.utils.UserSession;
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
  private Cart cart;
  private UserSession userSession;

  @GetMapping
  private String getOrders(Model model) {
    List<Order> orders = orderService.findAll();
    model.addAttribute("orders", orders);
    return "order";
  }

  @GetMapping("/place")
  private String placeOrder(Model model) {
    userSession.addOrder(new Order(cart.getPrice(), cart.getContainer(), userSession.getCustomer()));
    model.addAttribute("order", userSession);
    return "order";
  }

  @GetMapping("/create")
  private String createOrder() {
    for (Order o: userSession.getOrderList()) {
      orderService.createOrder(o);
    }
    return "redirect:/orders";
  }

}
