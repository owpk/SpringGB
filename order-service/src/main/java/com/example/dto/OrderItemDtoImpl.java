package com.example.dto;

import com.example.OrderDto;
import com.example.entities.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDtoImpl implements OrderDto {
    private Long productId;
    private String productTitle;
    private String categoryTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDtoImpl(OrderItem o) {
        this.productId = o.getProduct().getId();
        this.productTitle = o.getProduct().getTitle();
        this.categoryTitle = o.getProduct().getCategory().getTitle();
        this.quantity = o.getQuantity();
        this.pricePerProduct = o.getPricePerProduct();
        this.price = o.getPrice();
    }
}
