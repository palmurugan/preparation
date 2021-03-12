package com.virtualstore.product.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

  private Long id;

  private CategoryDTO category;

  private String name;

  private String description;

  private BigDecimal price;

  private String status;
}
