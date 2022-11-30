package com.moa.moabackend.chat.config;

import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRedisRepository extends CrudRepository<ChatRoom, Long> {

}
