package org.owpk.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements EntityModel {
  private int id;
  private String title;
  private int cost;
  private Category category;

}
