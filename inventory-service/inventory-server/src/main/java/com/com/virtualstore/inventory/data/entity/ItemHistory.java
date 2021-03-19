package com.com.virtualstore.inventory.data.entity;

import com.virtualstore.inventory.dto.HistoryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class ItemHistory {

  private String id;

  private String itemId;

  private Double quantity;

  private HistoryType type;

}
