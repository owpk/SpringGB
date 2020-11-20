package com.example.dto;

import com.example.entities.Order;
import com.example.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class OrderDtoImpl implements OrderDto {
    private List<OrderItemDtoImpl> items;
    private int price;
    private String address;

    public OrderDtoImpl(Order o) {
        this.items = o.getItems().stream().map(OrderItemDtoImpl::new).collect(Collectors.toList());
        this.price = o.getPrice();
        this.address = o.getAddress();
    }
}
