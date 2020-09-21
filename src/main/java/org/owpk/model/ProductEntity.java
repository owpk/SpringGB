package org.owpk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private int cost;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "cart",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "customer_id")
  )
  private List<CustomerEntity> customers;

  @Override
  public String toString() {
    return "{id=" + id +
        ", name='" + name + '\'' +
        ", cost=" + cost + '}';
  }
}
