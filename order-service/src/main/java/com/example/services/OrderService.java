package com.example.services;

import com.example.entities.Order;
import com.example.OrderDto;
import com.example.dto.OrderDtoImpl;
import com.example.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderDto> findAllUserOrdersDtosByUsername(String username) {
        return orderRepository.findAllOrdersByUsername(username).stream().map(OrderDtoImpl::new).collect(Collectors.toList());
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
