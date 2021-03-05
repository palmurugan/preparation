package com.pal.userservice.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Identity Access Management configuration - (Keycloak) */
@Configuration
public class KeycloakConfig {

  @Value("${keycloak.auth-server-url}")
  private String authURL;

  @Value("${keycloak.realm}")
  private String realm;

  @Value("${keycloak.resource}")
  private String clientId;

  @Value("${keycloak.credentials.secret}")
  private String clientSecret;

  @Bean
  public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(authURL)
        .realm(realm)
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }
}
