package com.virtualstore.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemHistoryDTO {

  private String id;

  private String itemId;

  private Double quantity;

  private HistoryType type;
}
