package com.com.virtualstore.inventory.business.service.impl;

import com.com.virtualstore.inventory.business.mapper.ItemHistoryMapper;
import com.com.virtualstore.inventory.business.service.ItemHistoryService;
import com.com.virtualstore.inventory.data.repository.ItemHistoryRepository;
import com.virtualstore.inventory.dto.ItemHistoryDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemHistoryServiceImpl implements ItemHistoryService {

  private final ItemHistoryRepository itemHistoryRepository;

  private final ItemHistoryMapper itemHistoryMapper;

  public ItemHistoryServiceImpl(
      ItemHistoryRepository itemHistoryRepository, ItemHistoryMapper itemHistoryMapper) {
    this.itemHistoryRepository = itemHistoryRepository;
    this.itemHistoryMapper = itemHistoryMapper;
  }

  @Override
  public Mono<ItemHistoryDTO> save(ItemHistoryDTO itemHistoryDTO) {
    return itemHistoryRepository.save(itemHistoryMapper.toEntity(itemHistoryDTO))
        .flatMap(itemHistory -> Mono.just(itemHistoryMapper.toDto(itemHistory)));
  }

  @Override
  public Flux<ItemHistoryDTO> findAll() {
    return itemHistoryRepository.findAll()
        .flatMap(itemHistory -> Flux.just(itemHistoryMapper.toDto(itemHistory)));
  }

  @Override
  public Mono<ItemHistoryDTO> findById(String id) {
    return itemHistoryRepository.findById(id)
        .flatMap(itemHistory -> Mono.just(itemHistoryMapper.toDto(itemHistory)));
  }

  @Override
  public Mono<Void> delete(String id) {
    return itemHistoryRepository.deleteById(id);
  }
}
