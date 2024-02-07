package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByIdIn(Collection<Long> userIds);

    Optional<User> findByProviderKey(String providerKey);

    Optional<User> findByUserEmail(String userEmail);

    List<User> findAllByUserNickNameStartsWith(String userNickname);

    Optional<User> findTopByOrderByIdDesc();

    Optional<User> findByUserNickName(String userNickName);

}
