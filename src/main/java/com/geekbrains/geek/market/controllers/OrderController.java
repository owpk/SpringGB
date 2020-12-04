package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.CartDto;
import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.dto.UserDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private Cart cart;

    @GetMapping("/check")
    public String checkIfAlive() {
        return "Hi!";
    }

    @GetMapping("/test")
    public List<OrderDto> createFakeOder() {
        System.out.println("---TEST REQUEST---");
        cart.addOrIncrement(1L);
        cart.addOrIncrement(2L);
        Order order = new Order(userService.findUserById(1L), cart, "NYC Abc St.");
        orderService.save(order);
        return orderService.findAll()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal) {

        return principal.getName();
    }

    @PostMapping(consumes = "application/json")
    @RequestMapping("/confirm")
    public void createProduct(@RequestBody OrderDto[] o) {
        System.out.println("------------------");
        if (o != null && o.length > 0) {
            System.out.println(o[0].getUsername());
            User u = userService.findByUsername(o[0].getUsername());
            Arrays.stream(o).map(x -> new Order(u, cart, x.getAddress())).forEach(orderService::save);
        }
        System.out.println(orderService.findAll());
    }

//    @PostMapping
//    public String confirmOrder() {
//        User user = userService.findByUsername();
//        Order order = new Order(user, cart, );
//        order = orderService.save(order);
//        return "Ваш заказ #" + order.getId();
//    }


}
