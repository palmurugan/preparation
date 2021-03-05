package com.pal.userservice.data.repository;

import com.github.palmurugan.common.repository.DataRepository;
import com.pal.userservice.data.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DataRepository<User, Long> {

}
