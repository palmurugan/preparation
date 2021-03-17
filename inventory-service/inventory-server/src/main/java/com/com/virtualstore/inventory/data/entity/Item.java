package com.com.virtualstore.inventory.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Item {

  @Id
  private String id;

  private String productId;

  private String sku;

  private Double quantity;

  private Double price;

  private String unit;

  private String status;
}
