package org.owpk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "cart",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<ProductEntity> products;

  @Override
  public String toString() {
    return "{id=" + id +
        ", name='" + name + '}';
  }
}
