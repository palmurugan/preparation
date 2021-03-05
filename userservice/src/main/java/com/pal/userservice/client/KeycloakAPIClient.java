package com.pal.userservice.client;

import com.pal.userservice.data.entity.User;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.ws.rs.core.Response;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The purpose of the client is used to access Keycloak admin API's (REST)
 *
 * <p>We are using Spring Keycloak admin client API for the same.
 */
@Service
public class KeycloakAPIClient implements IdentityServiceAPIClient {

  private static final String USER_ROLE = "user";

  private static final Logger logger = LoggerFactory.getLogger(KeycloakAPIClient.class);
  /** Function to fetch userId */
  private final Function<Response, String> fetchUserId = CreatedResponseUtil::getCreatedId;
  /** Consumer to reset the password */
  private final BiConsumer<UserResource, CredentialRepresentation> resetPassword =
      UserResource::resetPassword;
  /** Updating User role */
  private final BiConsumer<UserResource, List<RoleRepresentation>> updateRoleAgainstUser =
      (userResource, roleRepresentations) ->
          userResource.roles().realmLevel().add(roleRepresentations);

  private Keycloak keycloak;

  @Value("${keycloak.realm}")
  private String realm;
  /** Function to create a new user */
  private final Function<UserRepresentation, Response> createUser =
      userRepresentation -> keycloak.realm(realm).users().create(userRepresentation);
  /** Function to fetch user details */
  private final Function<String, UserResource> fetchUserDetails =
      userId -> keycloak.realm(realm).users().get(userId);
  /** Function to fetch role representation */
  private final Function<UserResource, RoleRepresentation> fetchUserRole =
      userResource -> keycloak.realm(realm).roles().get(USER_ROLE).toRepresentation();

  public KeycloakAPIClient(Keycloak keycloak) {
    this.keycloak = keycloak;
  }

  @Override
  public void saveUser(User user) {
    logger.debug("Keycloak user creation process started: {}", user);
    Optional<UserRepresentation> userRepresentation = prepareUserRequest(user);
    Optional<CredentialRepresentation> credentialRepresentation = prepareCredential(user);
    if (userRepresentation.isPresent() && credentialRepresentation.isPresent()) {
      // Create user and update password
      UserResource userResource =
          createUser.andThen(fetchUserId).andThen(fetchUserDetails).apply(userRepresentation.get());
      resetPassword.accept(userResource, credentialRepresentation.get());

      // Update role against the user
      updateRoleAgainstUser.accept(
          userResource, Collections.singletonList(fetchUserRole.apply(userResource)));
    } else {
      logger.error("Invalid user information provided");
    }
  }

  private Optional<UserRepresentation> prepareUserRequest(User userEntity) {
    return Optional.of(userEntity)
        .map(
            userObject -> {
              UserRepresentation user = new UserRepresentation();
              user.setUsername(userObject.getUserName());
              user.setFirstName(userObject.getFirstName());
              user.setLastName(userObject.getLastName());
              user.setEmail(userObject.getEmail());
              user.setEnabled(Boolean.TRUE);
              return user;
            });
  }

  private Optional<CredentialRepresentation> prepareCredential(User userEntity) {
    return Optional.of(userEntity)
        .map(
            userObject -> {
              CredentialRepresentation credential = new CredentialRepresentation();
              credential.setTemporary(Boolean.FALSE);
              credential.setType(CredentialRepresentation.PASSWORD);
              credential.setValue(userObject.getPassword());
              return credential;
            });
  }
}
