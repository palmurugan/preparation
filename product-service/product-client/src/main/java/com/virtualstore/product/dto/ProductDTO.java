package com.virtualstore.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProductDTO {

  private String id;
  
  private String categoryId;

  private String category;

  private String name;

  private String summary;

  private String status;

}
