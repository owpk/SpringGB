package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllUserOrdersDtosByUsername(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address);
        orderService.save(order);
        cart.clear();
    }
}
