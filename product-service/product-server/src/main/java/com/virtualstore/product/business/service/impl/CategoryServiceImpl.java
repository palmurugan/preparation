package com.virtualstore.product.business.service.impl;

import com.virtualstore.product.business.mapper.CategoryMapper;
import com.virtualstore.product.business.service.CategoryService;
import com.virtualstore.product.data.entity.Category;
import com.virtualstore.product.data.repository.CategoryRepository;
import com.virtualstore.product.dto.CategoryDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
  }

  public Mono<CategoryDTO> save(CategoryDTO categoryDTO) {
    Category category = categoryMapper.toEntity(categoryDTO);
    return categoryRepository
        .save(category)
        .flatMap(categoryEntity -> Mono.just(categoryMapper.toDto(categoryEntity)));
  }

  @Override
  public Flux<CategoryDTO> findAll() {
    return categoryRepository.findAll()
        .flatMap(category -> Flux.just(categoryMapper.toDto(category)));
  }

  @Override
  public Mono<CategoryDTO> findById(String id) {
    return categoryRepository.findById(id)
        .flatMap(category -> Mono.justOrEmpty(categoryMapper.toDto(category)));
  }
}
