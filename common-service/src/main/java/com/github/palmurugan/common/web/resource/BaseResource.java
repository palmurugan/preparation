package com.github.palmurugan.common.web.resource;

import com.github.palmurugan.common.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseResource<E, K> {

  private GenericService<E, K> baseService;

  public BaseResource(GenericService<E, K> baseService) {
    this.baseService = baseService;
  }

  @PostMapping
  public ResponseEntity<E> save(@RequestBody  E entity) {
    return new ResponseEntity<>(baseService.save(entity), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Page<E>> listAll(Pageable pageable) {
    return new ResponseEntity<>(baseService.findAll(pageable), HttpStatus.OK);
  }
}
