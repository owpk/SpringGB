package com.owpk.hw.utils;

import com.owpk.hw.entities.Order;
import com.owpk.hw.entities.OrderItem;
import com.owpk.hw.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@SessionScope
@NoArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;
    private Integer price;

    @PostConstruct
    private void init() {
        items = new ArrayList<>();
    }
    public void addOrIncrement(Product p) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(p.getId())) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(p));
        recalculate();
    }

    public void incrementOnly(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
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
            price += o.getPrice();
        }
    }

    public void clear() {
        items.clear();
        price = 0;
    }
}
