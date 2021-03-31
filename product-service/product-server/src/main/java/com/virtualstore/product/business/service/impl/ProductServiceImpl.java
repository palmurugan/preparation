package com.virtualstore.product.business.service.impl;

import com.virtualstore.inventory.dto.ItemDTO;
import com.virtualstore.product.business.mapper.ProductMapper;
import com.virtualstore.product.business.service.CategoryService;
import com.virtualstore.product.business.service.ProductService;
import com.virtualstore.product.data.entity.Product;
import com.virtualstore.product.data.repository.ProductRepository;
import com.virtualstore.product.dto.ProductDTO;
import com.virtualstore.reactive.common.client.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** @author palmurugan */
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final CategoryService categoryService;

  private final ProductMapper productMapper;

  @Autowired private RemoteClient<ItemDTO> remoteClient;

  public ProductServiceImpl(
      ProductRepository productRepository,
      ProductMapper productMapper,
      CategoryService categoryService) {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
    this.productMapper = productMapper;
  }

  /**
   * The purpose of this function is save/update the product entry
   *
   * @param productDTO
   * @return
   */
  @Override
  public Mono<ProductDTO> save(ProductDTO productDTO) {
    Product product = productMapper.toEntity(productDTO);
    return productRepository
        .save(product)
        .flatMap(productEntity -> Mono.just(productMapper.toDto(productEntity)));
  }

  /**
   * In this function we list all products along with the category names. Fetching products and
   * corresponding category both are different calls
   *
   * @return
   */
  @Override
  public Flux<ProductDTO> findAll() {
    remoteClient
        .get("http://localhost:8083/api/inventories/items/605c995e1647dd098d8289d9", ItemDTO.class)
        .subscribe(itemDTO -> System.out.println("Quantity -->" + itemDTO.getQuantity()));
    return productRepository
        .findAll()
        .flatMap(product -> Flux.just(productMapper.toDto(product)))
        .flatMap(
            productDTO ->
                Mono.just(categoryService.findById(productDTO.getCategoryId()))
                    .flatMap(
                        categoryMono ->
                            categoryMono.flatMap(
                                categoryEntity -> {
                                  productDTO.setCategory(categoryEntity.getName());
                                  return Mono.just(productDTO);
                                })));
  }

  @Override
  public Mono<ProductDTO> findById(String id) {
    return productRepository
        .findById(id)
        .flatMap(product -> Mono.justOrEmpty(productMapper.toDto(product)));
  }
}
