package com.com.virtualstore.inventory.business.service;

import com.virtualstore.inventory.dto.ItemDTO;
import reactor.core.publisher.Mono;

public interface ItemService extends GenericService<ItemDTO, String> {

  Mono<ItemDTO> findByProductId(String productId);
}
