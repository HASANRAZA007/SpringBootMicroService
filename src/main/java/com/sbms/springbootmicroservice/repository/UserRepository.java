package com.sbms.springbootmicroservice.repository;

import com.sbms.springbootmicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    User findUserByUserName(String userName);
}