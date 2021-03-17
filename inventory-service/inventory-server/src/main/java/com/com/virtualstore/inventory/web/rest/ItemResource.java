package com.com.virtualstore.inventory.web.rest;

import com.com.virtualstore.inventory.business.service.ItemService;
import com.com.virtualstore.inventory.web.error.BadRequestAlertException;
import com.virtualstore.inventory.dto.ItemDTO;
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
@RequestMapping("/api/inventories/items")
public class ItemResource {

  private static final String ITEM_ENTITY = "items";
  private static final String ID_EXISTS = "id.exists";
  private static final String ID_NOT_EXISTS = "id.notexists";
  private final ItemService itemService;

  public ItemResource(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ItemDTO> save(@RequestBody ItemDTO itemDTO) {
    Optional.of(itemDTO)
        .filter(item -> item.getId() == null)
        .orElseThrow(
            () ->
                new BadRequestAlertException(
                    "New item cannot already have an Id", ITEM_ENTITY, ID_EXISTS));
    return itemService.save(itemDTO);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<ItemDTO> update(@RequestBody ItemDTO itemDTO) {
    Optional.of(itemDTO)
        .filter(item -> item.getId() != null)
        .orElseThrow(
            () -> new BadRequestAlertException("Invalid Request", ITEM_ENTITY, ID_NOT_EXISTS));
    return itemService.save(itemDTO);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<ItemDTO> fetchById(@PathVariable String id) {
    return itemService.findById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<ItemDTO> fetchAll() {
    return itemService.findAll();
  }
}
