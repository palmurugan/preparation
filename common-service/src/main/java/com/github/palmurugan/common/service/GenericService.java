package com.github.palmurugan.common.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Generic service interface for all common operations
 *
 * @param <E> - Entity
 * @param <K> - Key
 */
public interface GenericService<E, K> {

  E save(E entity);

  void saveOrUpdateAll(List<E> entities);

  Optional<E> findByKey(K id);

  Page<E> findAll(Pageable pageable);

  List<E> findAll();

  void delete(K id);
}
