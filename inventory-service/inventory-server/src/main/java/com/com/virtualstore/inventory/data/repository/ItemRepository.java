package com.com.virtualstore.inventory.data.repository;

import com.com.virtualstore.inventory.data.entity.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ItemRepository extends ReactiveMongoRepository<Item, String> {

  Mono<Item> findByProductIdAndSku(String productId, String sku);
}
