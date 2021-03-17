package com.com.virtualstore.inventory.business.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<D, K> {

  Mono<D> save(D dto);

  Flux<D> findAll();

  Mono<D> findById(K id);

  Mono<Void> delete(K id);
}
