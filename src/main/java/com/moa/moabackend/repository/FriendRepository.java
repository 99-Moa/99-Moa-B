package com.moa.moabackend.repository;

import com.moa.moabackend.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUserId(String userId);
//        Optional<Friend> deleteByUserIdAndFriendUsername(String userId, String friendUsername);
}
