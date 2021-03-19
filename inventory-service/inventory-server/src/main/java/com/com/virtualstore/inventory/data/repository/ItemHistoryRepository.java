package com.com.virtualstore.inventory.data.repository;

import com.com.virtualstore.inventory.data.entity.ItemHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemHistoryRepository extends ReactiveMongoRepository<ItemHistory, String> {
  
}
