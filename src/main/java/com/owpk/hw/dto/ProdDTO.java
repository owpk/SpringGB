package com.owpk.hw.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@NoArgsConstructor
@Data
public class ProdDTO {
  private Long id;
  private String title;
  private Integer price;

  public ProdDTO(Long id, String title, Integer price) {
    this.id = id;
    this.title = title;
    this.price = price;
  }
}

