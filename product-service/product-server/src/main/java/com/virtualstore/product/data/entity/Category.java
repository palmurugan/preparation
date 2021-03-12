package com.virtualstore.product.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {

  @Id private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "status", nullable = false)
  private String status;
}
