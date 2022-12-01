package com.moa.moabackend.chat.repository;

import com.moa.moabackend.chat.entity.ChatRoom;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRedisRepository extends CrudRepository<ChatRoom, Long> {

}