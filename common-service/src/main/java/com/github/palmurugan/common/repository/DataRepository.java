package com.github.palmurugan.common.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository<E, K> extends CrudRepository<E, K> {

  @Override
  List<E> findAll();

  Page<E> findAll(Pageable pageable);
}
