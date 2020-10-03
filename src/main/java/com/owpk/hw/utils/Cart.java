package com.owpk.hw.utils;

import com.owpk.hw.entities.OrderItem;
import com.owpk.hw.entities.Product;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Component
@SessionScope
public class Cart {
    private List<OrderItem> container;
    private int price;

    public Cart() {
        container = new ArrayList<>();
    }

    public void addOrIncrement(Product p) {
        for (OrderItem o : container) {
            if (o.getProduct().getId().equals(p.getId())) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        container.add(new OrderItem(p));
        recalculate();
    }

    public void incrementOnly(Long productId) {
        for (OrderItem o : container) {
            if (o.getProduct().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = container.iterator();
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
        Iterator<OrderItem> iter = container.iterator();
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
        for (OrderItem o : container) {
            price += o.getPrice();
        }
    }

}
