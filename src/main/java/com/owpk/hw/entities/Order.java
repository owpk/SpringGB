package com.owpk.hw.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer price;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> items;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Order(Integer price, List<OrderItem> items, Customer customer) {
    this.price = price;
    this.items = items;
    this.customer = customer;
  }
}
