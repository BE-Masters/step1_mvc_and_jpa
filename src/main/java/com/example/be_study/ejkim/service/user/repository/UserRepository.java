package com.example.be_study.ejkim.service.user.repository;


public class UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
}
