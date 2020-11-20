package com.example.controllers;

import com.example.OrderDto;
import com.example.entities.Cart;
import com.example.entities.Order;
import com.example.entities.User;
import com.example.services.OrderService;
import com.example.utils.CartImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAllUserOrdersDtosByUsername("user");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrderTest(@RequestParam String address) {
        User fakeUser = new User();
        fakeUser.setUsername("user");
        Cart fakeCart = new CartImpl();
        fakeCart.addOrIncrement(1L);
        fakeCart.addOrIncrement(2L);
        Order order = new Order(fakeUser, fakeCart, address);
        orderService.save(order);
        fakeCart.clear();
    }
}
