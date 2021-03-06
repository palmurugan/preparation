package com.pal.userservice.business.event.consumers;

import com.github.palmurugan.common.event.DataEvent;
import com.pal.userservice.client.IdentityServiceAPIClient;
import com.pal.userservice.data.entity.User;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

  private static final Logger logger = LoggerFactory.getLogger(UserEventConsumer.class);

  private final IdentityServiceAPIClient identityServiceAPIClient;

  public UserEventConsumer(IdentityServiceAPIClient identityServiceAPIClient) {
    this.identityServiceAPIClient = identityServiceAPIClient;
  }

  @Bean
  public Consumer<DataEvent<String, User>> userConsumer() {
    return event -> identityServiceAPIClient.createUser(event.getData());
  }
}
