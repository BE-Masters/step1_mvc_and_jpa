package com.example.be_study.service.user.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByIdIn(Collection<Long> userIds);

    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByUserNickName(String userNickname);
}
