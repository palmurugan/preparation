package com.virtualstore.product.business.service;

import com.virtualstore.product.dto.CategoryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Mono<CategoryDTO> save(CategoryDTO categoryDTO);

  Flux<CategoryDTO> findAll();

  Mono<CategoryDTO> findById(String id);
}
