package com.owpk.hw.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer quantity;
  private Integer price;
  @Column(name = "price_per_product")
  private Integer pricePerProduct;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  public OrderItem(Product p) {
    this.product = p;
    this.quantity = 1;
    this.price = p.getPrice();
    this.pricePerProduct = p.getPrice();
  }

  @Override
  public String toString() {
    return "OrderItem{" +
        "quantity=" + quantity +
        ", price=" + price +
        ", product=" + product +
        '}';
  }

  public void incrementQuantity() {
    quantity++;
    price = pricePerProduct * quantity;
  }

  public void decrementQuantity() {
    quantity--;
    price = pricePerProduct * quantity;
  }
}
