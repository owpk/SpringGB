package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
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
    public List<OrderDto> showOrders() {
        cart.addOrIncrement(1L);
        cart.addOrIncrement(2L);
        Order order = new Order(userService.findUserById(1L), cart, "NYC Foobar St.");
        orderService.save(order);
        return orderService.findAll()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal) {
//        model.addAttribute("username", principal.getName());
        return principal.getName();
    }

//    @PostMapping("/confirm")
//    public String confirmOrder(Principal principal,
//                               @RequestParam(name = "address") String address,
//                               @RequestParam(name = "receiver_name") String receiverName,
//                               @RequestParam(name = "phone_number") String phone
//    ) {
//        User user = userService.findByUsername(principal.getName());
//        Order order = new Order(user, cart, address);
//        order = orderService.save(order);
//        return "Ваш заказ #" + order.getId();
//    }


}
