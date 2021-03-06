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

/** @author palmuruganc */
@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  private final ItemHistoryService itemHistoryService;

  private ItemMapper itemMapper;
  /** Function to convert Item entity to Item DTO */
  private final Function<Item, Mono<ItemDTO>> convertToItemDTO =
      item -> Mono.just(itemMapper.toDto(item));

  private final Function<Item, Flux<ItemDTO>> convertToItemDTOList =
      item -> Flux.just(itemMapper.toDto(item));

  public ItemServiceImpl(
      ItemRepository itemRepository, ItemMapper itemMapper, ItemHistoryService itemHistoryService) {
    this.itemRepository = itemRepository;
    this.itemMapper = itemMapper;
    this.itemHistoryService = itemHistoryService;
  }

  @Override
  public Mono<ItemDTO> findById(String id) {
    return itemRepository.findById(id).flatMap(convertToItemDTO);
  }

  /**
   * Function to save/update items based on SKU. Based on the type the quantity will get update.
   *
   * <p>ItemHistory will also get persisted(This is based on each record)
   *
   * @param itemDTO
   * @return
   */
  @Override
  public Mono<ItemDTO> save(ItemDTO itemDTO) {
    return this.saveItem(itemDTO)
        .flatMap(item -> this.prepareItemHistory(item, itemDTO))
        .flatMap(itemHistoryService::save)
        .flatMap(itemHistoryDTO -> Mono.just(itemHistoryDTO.getItemId()))
        .flatMap(this::findById);
  }

  @Override
  public Flux<ItemDTO> findAll() {
    return itemRepository.findAll().flatMap(convertToItemDTOList);
  }

  @Override
  public Mono<ItemDTO> findByProductId(String productId) {
    return itemRepository.findByProductId(productId).flatMap(convertToItemDTO);
  }

  @Override
  public Mono<Void> delete(String id) {
    return null;
  }

  private Mono<ItemDTO> saveItem(ItemDTO itemDTO) {
    return itemRepository
        .findBySku(itemDTO.getSku())
        .flatMap(item -> this.formItem(item, itemDTO))
        .flatMap(itemRepository::save)
        .flatMap(convertToItemDTO)
        .switchIfEmpty(
            Mono.defer(() -> itemRepository.save(itemMapper.toEntity(itemDTO)))
                .flatMap(convertToItemDTO));
  }

  private Mono<Item> formItem(Item item, ItemDTO itemDTO) {
    if (itemDTO.getType().equals(HistoryType.CIN)
        || itemDTO.getType().equals(HistoryType.CIN_ADJ_ADD)
        || itemDTO.getType().equals(HistoryType.CIN_RET)) {
      item.setQuantity(item.getQuantity() + itemDTO.getQuantity());
    } else {
      item.setQuantity(item.getQuantity() - itemDTO.getQuantity());
    }
    return Mono.just(item);
  }

  /**
   * Preparing data for history with new Quantity
   *
   * @param itemDTO Persisted data which is having the item state (ItemId)
   * @param newItemDTO The new data comes from the request
   * @return ItemHistory function
   */
  private Mono<ItemHistoryDTO> prepareItemHistory(ItemDTO itemDTO, ItemDTO newItemDTO) {
    ItemHistoryDTO itemHistory = new ItemHistoryDTO();
    itemHistory.setItemId(itemDTO.getId());
    itemHistory.setQuantity(newItemDTO.getQuantity());
    itemHistory.setType(HistoryType.CIN);
    return Mono.just(itemHistory);
  }
}
