package com.github.palmurugan.common.service.impl;

import com.github.palmurugan.common.repository.DataRepository;
import com.github.palmurugan.common.service.GenericService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Generic implementation for all common operations
 *
 * @author palmuruganc
 *
 * @param <E>
 * @param <K>
 */
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

  private final DataRepository<E, K> repository;

  public GenericServiceImpl(DataRepository<E, K> repository) {
    this.repository = repository;
  }

  @Override
  public E save(E entity) {
    return repository.save(entity);
  }

  @Override
  public void saveOrUpdateAll(List<E> entities) {
    repository.saveAll(entities);
  }

  @Override
  public Optional<E> findByKey(K id) {
    return repository.findById(id);
  }

  @Override
  public Page<E> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<E> findAll() {
    return repository.findAll();
  }

  @Override
  public void delete(K id) {
    repository.deleteById(id);
  }
}
