package com.com.virtualstore.inventory.business.service.impl;

import com.com.virtualstore.inventory.business.mapper.ItemMapper;
import com.com.virtualstore.inventory.business.service.ItemService;
import com.com.virtualstore.inventory.data.repository.ItemRepository;
import com.virtualstore.inventory.dto.ItemDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  private final ItemMapper itemMapper;

  public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
    this.itemRepository = itemRepository;
    this.itemMapper = itemMapper;
  }

  @Override
  public Mono<ItemDTO> findById(String id) {
    return itemRepository.findById(id).flatMap(item -> Mono.just(itemMapper.toDto(item)));
  }

  @Override
  public Mono<ItemDTO> save(ItemDTO itemDTO) {
    return itemRepository
        .save(itemMapper.toEntity(itemDTO))
        .flatMap(item -> Mono.just(itemMapper.toDto(item)));
  }

  @Override
  public Flux<ItemDTO> findAll() {
    return itemRepository.findAll().flatMap(items -> Flux.just(itemMapper.toDto(items)));
  }

  @Override
  public Mono<Void> delete(String id) {
    return null;
  }
}
