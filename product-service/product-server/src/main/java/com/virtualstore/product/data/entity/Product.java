package com.virtualstore.product.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@NoArgsConstructor
public class Product {

  @Id
  private String id;

  private String categoryId;

  private String name;

  private String summary;

  private String status;

}
