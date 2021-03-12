package com.virtualstore.product.web.rest;

import com.virtualstore.product.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

  @GetMapping
  public Mono<ProductDTO> findProduct() {
    return Mono.just(new ProductDTO());
  }
}
