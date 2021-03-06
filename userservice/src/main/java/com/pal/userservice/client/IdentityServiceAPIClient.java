package com.pal.userservice.client;

import com.pal.userservice.data.entity.User;

public interface IdentityServiceAPIClient {

  void createUser(User user);
}
