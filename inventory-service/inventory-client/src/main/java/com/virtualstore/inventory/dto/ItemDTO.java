package com.virtualstore.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

  private String id;

  private String productId;

  private String sku;

  private Double quantity;

  private Double price;

  private String unit;

  private String status;

  private HistoryType type;
}
