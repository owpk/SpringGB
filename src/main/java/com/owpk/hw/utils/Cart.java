package com.owpk.hw.utils;

import com.owpk.hw.entities.OrderItem;
import com.owpk.hw.entities.Product;
import com.owpk.hw.exceptions.ResourceNotFoundException;
import com.owpk.hw.services.ProductService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@SessionScope
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + productId + " (add to cart)"));
        items.add(new OrderItem(p));
        recalculate();
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItem o : items) {
            o.setPricePerProduct(o.getProduct().getPrice());
            o.setPrice(o.getProduct().getPrice() * o.getQuantity());
            price += o.getPrice();
        }
    }

    public void clear() {
        items.clear();
        price = 0;
    }
}
