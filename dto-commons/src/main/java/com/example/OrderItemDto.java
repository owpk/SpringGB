package com.example;

public interface OrderItemDto {
    Long getProductId();
    String getProductTitle();
    String getCategoryTitle();
    Integer getQuantity();
    Integer getPricePerProduct();
    Integer getPrice();
}
