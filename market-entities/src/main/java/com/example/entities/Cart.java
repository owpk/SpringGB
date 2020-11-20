package com.example.entities;

import java.util.List;

public interface Cart {
    List<OrderItem> getItems();
    int getPrice();
    void clear();
    void addOrIncrement(Long productId);
    void decrementOrRemove(Long productId);
    void remove(Long productId);
    void recalculate();
}
