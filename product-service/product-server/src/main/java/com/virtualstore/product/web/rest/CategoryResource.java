package com.virtualstore.product.web.rest;

import com.virtualstore.product.business.service.CategoryService;
import com.virtualstore.product.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

  private final CategoryService categoryService;

  public CategoryResource(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
    return categoryService.save(categoryDTO);
  }
}