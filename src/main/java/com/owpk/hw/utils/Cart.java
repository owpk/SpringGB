package com.owpk.hw.utils;

import com.owpk.hw.entities.OrderItem;
import com.owpk.hw.entities.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Component
@SessionScope
public class Cart {
  private List<OrderItem> orderItemList;
  private Integer price;

  @PostConstruct
  private void init() {
    orderItemList = new ArrayList<>();
  }

  public void addOrIncrement(Product p) {
    for (OrderItem oi: orderItemList) {
      if (oi.getProduct().getId().equals(p.getId())) {
        oi.incrementQuantity();
        recalculate();
        return;
      }
    }
    orderItemList.add(new OrderItem(p));
  }

  public void decrementOrDelete(Product p) {
    Iterator<OrderItem> iterator = orderItemList.listIterator();
    while (iterator.hasNext()) {
      OrderItem next = iterator.next();
      if (next.getId().equals(p.getId()))
        next.decrementQuantity();
      if (next.getQuantity() == 0) {
        iterator.remove();
        recalculate();
        return;
      }
    }
  }

  private void recalculate() {
    price = 0;
    for (OrderItem oi: orderItemList) {
      price += oi.getPrice();
    }
  }

}
