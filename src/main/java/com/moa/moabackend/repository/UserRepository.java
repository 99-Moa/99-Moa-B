package com.moa.moabackend.repository;

import com.moa.moabackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByUserName(String userName);


}


