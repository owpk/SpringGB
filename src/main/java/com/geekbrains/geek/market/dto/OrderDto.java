package com.geekbrains.geek.market.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonAutoDetect
@NoArgsConstructor
public class OrderDto {

    @JsonFormat(pattern = "dd-MM-yy HH:mm", locale = "US")
    private Date timestamp;
    private Long id;
    private String username;
    private String email;
    private List<OrderItemDto> orderItemDtoList;
    private Integer price;
    private String address;

    public OrderDto(Order order) {
        timestamp = new Date();
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.email = order.getUser().getEmail();
        this.orderItemDtoList = order.getItems()
                .stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
        this.price = order.getPrice();
        this.address = order.getAddress();
    }
}
