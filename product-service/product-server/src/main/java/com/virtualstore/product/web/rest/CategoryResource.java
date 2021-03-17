package com.virtualstore.product.web.rest;

import com.virtualstore.product.business.service.CategoryService;
import com.virtualstore.product.dto.CategoryDTO;
import com.virtualstore.product.web.error.BadRequestAlertException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

  private static final String ENTITY_NAME = "Category";
  private final CategoryService categoryService;

  public CategoryResource(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
    Optional.of(categoryDTO).filter(category -> category.getId() == null).orElseThrow(() ->
        new BadRequestAlertException("New category cannot already have an ID", ENTITY_NAME,
            "id.exists")
    );
    return categoryService.save(categoryDTO);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
    Optional.of(categoryDTO).filter(category -> category.getId() != null).orElseThrow(
        () -> new BadRequestAlertException("Invalid Id", ENTITY_NAME, "id.notexists")
    );
    return categoryService.save(categoryDTO);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<CategoryDTO> findCategoryById(@PathVariable String id) {
    return categoryService.findById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<CategoryDTO> findAll() {
    return categoryService.findAll();
  }
}