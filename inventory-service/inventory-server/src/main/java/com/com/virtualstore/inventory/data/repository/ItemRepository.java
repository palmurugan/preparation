package com.com.virtualstore.inventory.data.repository;

import com.com.virtualstore.inventory.data.entity.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ReactiveMongoRepository<Item, String> {

}
