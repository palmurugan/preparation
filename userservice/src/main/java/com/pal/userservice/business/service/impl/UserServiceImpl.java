package com.pal.userservice.business.service.impl;

import com.github.palmurugan.common.event.DataEvent;
import com.github.palmurugan.common.event.EventType;
import com.github.palmurugan.common.service.impl.GenericServiceImpl;
import com.pal.userservice.business.service.UserService;
import com.pal.userservice.data.entity.User;
import com.pal.userservice.data.repository.UserRepository;
import java.util.Optional;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author palmuruganc */
@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

  private static final String USER_PRODUCER = "userProducer-out-0";
  private final StreamBridge streamBridge;
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository, StreamBridge streamBridge) {
    super(userRepository);
    this.streamBridge = streamBridge;
    this.userRepository = userRepository;
  }

  @Override
  public User save(User user) {
    User userCreated = userRepository.save(user);
    Optional.of(userCreated)
        .ifPresent(
            userObject ->
                streamBridge.send(
                    USER_PRODUCER,
                    new DataEvent<>(userObject.getId(), userObject, EventType.CREATE)));
    return userCreated;
  }
}
