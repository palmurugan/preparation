package com.com.virtualstore.inventory.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventories/items")
public class ItemResource {

  @GetMapping
  public Mono<String> test() {
    return Mono.just("Thank you!");
  }
}
