package com.com.virtualstore.inventory.business.service.impl;

import com.com.virtualstore.inventory.business.mapper.ItemMapper;
import com.com.virtualstore.inventory.business.service.ItemHistoryService;
import com.com.virtualstore.inventory.business.service.ItemService;
import com.com.virtualstore.inventory.data.entity.Item;
import com.com.virtualstore.inventory.data.repository.ItemRepository;
import com.virtualstore.inventory.dto.HistoryType;
import com.virtualstore.inventory.dto.ItemDTO;
import com.virtualstore.inventory.dto.ItemHistoryDTO;
import java.util.function.Function;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  private ItemHistoryService itemHistoryService;

  private ItemMapper itemMapper;
  /**
   * Function to convert Item entity to Item DTO
   */
  private final Function<Item, Mono<ItemDTO>> convertToItemDTO = item -> Mono
      .just(itemMapper.toDto(item));
  private final Function<Item, Flux<ItemDTO>> convertToItemDTOList = item -> Flux
      .just(itemMapper.toDto(item));

  public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper,
      ItemHistoryService itemHistoryService) {
    this.itemRepository = itemRepository;
    this.itemMapper = itemMapper;
    this.itemHistoryService = itemHistoryService;
  }

  @Override
  public Mono<ItemDTO> findById(String id) {
    return itemRepository.findById(id).flatMap(convertToItemDTO);
  }

  @Override
  public Mono<ItemDTO> save(ItemDTO itemDTO) {
    return this.saveItem(itemDTO).flatMap(this::prepareItemHistory)
        .flatMap(itemHistoryService::save)
        .flatMap(itemHistoryDTO -> Mono.just(itemHistoryDTO.getItemId())).flatMap(this::findById);
  }

  private Mono<ItemDTO> saveItem(ItemDTO itemDTO) {
    return itemRepository.save(itemMapper.toEntity(itemDTO)).flatMap(convertToItemDTO);
  }

  @Override
  public Flux<ItemDTO> findAll() {
    return itemRepository.findAll().flatMap(convertToItemDTOList);
  }

  @Override
  public Mono<Void> delete(String id) {
    return null;
  }

  private Mono<ItemHistoryDTO> prepareItemHistory(ItemDTO itemDTO) {
    ItemHistoryDTO itemHistory = new ItemHistoryDTO();
    itemHistory.setItemId(itemDTO.getId());
    itemHistory.setQuantity(itemDTO.getQuantity());
    itemHistory.setType(HistoryType.CIN);
    return Mono.just(itemHistory);
  }
}
