package com.moa.moabackend.repository;

import com.moa.moabackend.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByUserName(String userName);
    Optional<Friend> findByFriendUsername(String friendUsername);

}
