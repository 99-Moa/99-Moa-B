package com.moa.moabackend.repository;

import com.moa.moabackend.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
//    Optional<Friend> findByUserName(String userName);
//    Optional<Friend> findByFriendUsername(String friendUsername);

    List<Friend> findAllByUserId(String userId);


    Optional<Friend> deleteByUserIdAndFriendUsername(String userId, String friendUsername);
//    Optional<Friend> deleteByFriendUsername(String friendUsername);
//    Optional<Friend> findByUserId(String friendUsername);
}
