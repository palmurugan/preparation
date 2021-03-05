package com.pal.userservice.web.rest;

import com.github.palmurugan.common.web.resource.BaseResource;
import com.pal.userservice.business.service.UserService;
import com.pal.userservice.client.IdentityServiceAPIClient;
import com.pal.userservice.data.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserResource extends BaseResource<User, Long> {

  private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

  @Autowired private IdentityServiceAPIClient identityServiceAPIClient;

  private UserService userService;

  public UserResource(UserService userService) {
    super(userService);
    this.userService = userService;
  }
}
