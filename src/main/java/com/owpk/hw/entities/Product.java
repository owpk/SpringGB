package com.owpk.hw.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Integer price;

  @OneToMany(mappedBy = "product")
  List<OrderItem> orderItem;

  @Override
  public String toString() {
    return "Product{" + "ID" + id +
        "title='" + title + '\'' +
        ", price=" + price +
        '}';
  }
}
