package com.virtualstore.reactive.common.client;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RemoteClient<T> {

  private static final String AUTHORIZATION_HEADER = "Authorization";

  @Autowired private WebClient webClient;

  @Autowired private HttpServletRequest httpServletRequest;

  private String getAccessToken() {
    return httpServletRequest.getHeader(AUTHORIZATION_HEADER).split(" ")[1];
  }

  public Mono<T> get(String uri, Class<T> clz) {
    return webClient
        .get()
        .uri(uri)
        .headers(httpHeaders -> httpHeaders.setBearerAuth(getAccessToken()))
        .retrieve()
        .bodyToMono(clz);
  }
}
