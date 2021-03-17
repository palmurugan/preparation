package com.virtualstore.product.web.rest;

import com.virtualstore.product.business.service.ProductService;
import com.virtualstore.product.dto.ProductDTO;
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
@RequestMapping("/api/products")
public class ProductResource {

  private static final String ENTITY_NAME = "Product";

  private final ProductService productService;

  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
    Optional.of(productDTO).filter(product -> product.getId() == null).orElseThrow(() ->
        new BadRequestAlertException("New product cannot have an Id", ENTITY_NAME, "id.exists")
    );
    return productService.save(productDTO);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
    Optional.of(productDTO).filter(product -> product.getId() != null).orElseThrow(() ->
        new BadRequestAlertException("Invalid Identifier", ENTITY_NAME, "id.notexists")
    );
    return productService.save(productDTO);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<ProductDTO> findById(@PathVariable String id) {
    return productService.findById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<ProductDTO> findAllProduct() {
    return productService.findAll();
  }
}
