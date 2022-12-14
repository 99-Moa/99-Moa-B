//package com.moa.moabackend.chat.service;
//
//import com.moa.moabackend.chat.entity.ChatRoomRedis;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Service
//@Slf4j // logging
//@RequiredArgsConstructor
//public class ChatService {
//
//    // redis
//    private static final String CHAT_ROOMS = "CHAT_ROOM";
//    private final RedisTemplate<String, Object> redisTemplate;
//    private HashOperations<String, String, ChatRoomRedis> opsHashChatRoom;
//
//    @PostConstruct // Bean 의존관계 주입 완료 후, chatRoom 초기화.
//    private void init(){
//        // redis에다가 Hash 형태의 자료 생성
//        opsHashChatRoom = redisTemplate.opsForHash();
//    }
//
//    // 모든 채팅방 불러오기
//    public List<ChatRoomRedis> findAllRoom(){
//        return opsHashChatRoom.values(CHAT_ROOMS);
//    }
//
//    // 채팅방 하나 불러오기
//    public ChatRoomRedis findById(String roomId){
//        return opsHashChatRoom.get(CHAT_ROOMS, roomId);
//    }
//
//    // 채팅방 생성
//    public ChatRoomRedis createRoom(String name){
//        // 채팅방 생성
//        ChatRoomRedis chatRoom = ChatRoomRedis.create(name);
//        // 채팅방을 redis의 opsHashChatRoom에 삽입
//        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
//        return chatRoom;
//    }
//
//}
