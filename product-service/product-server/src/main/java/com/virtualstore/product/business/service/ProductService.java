package com.virtualstore.product.business.service;

import com.virtualstore.product.dto.CategoryDTO;
import com.virtualstore.product.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<ProductDTO> save(ProductDTO categoryDTO);

  Flux<ProductDTO> findAll();

  Mono<ProductDTO> findById(String id);

}
