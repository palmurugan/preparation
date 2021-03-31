package com.virtualstore.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailsDTO extends ProductDTO {

  private Double quantity;
}
