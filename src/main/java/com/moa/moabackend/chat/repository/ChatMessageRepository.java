package com.moa.moabackend.chat.repository;

import com.moa.moabackend.chat.entity.SocketMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<SocketMessage, Long> {
    List<SocketMessage> findAllByChatRoomId(Long chatRoomId);
}
