package com.example.be_study.choi.services.User.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserJpaRepository<T,ID> extends JpaRepository<User, Long> {
    Iterable<User> findAllByUserId(Long userId);
}
