package com.moa.moabackend.chat.config;

import com.moa.moabackend.entity.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRedisRepository chatRoomRedisRepository;
    // private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    // 채팅방 생성
    public ResponseDto<ChatRoomResponseDto> createRoom(ChatRoomRequestDto chatRoomRequestDto){
        List<String> users = new ArrayList<>();
        users.add("이동진");
        users.add("윤시원");
// 채팅방 저장
        ChatRoom chatRoom = ChatRoom.builder()
                .id(chatRoomRequestDto.getChatRoomId())
                .users(users)
                .build();
// chatRoomRepository.save(chatRoom);
        chatRoomRedisRepository.save(chatRoom);
        ChatRoomResponseDto chatRoomResponseDto = ChatRoomResponseDto.builder()
                .chatRoomId(chatRoom.getId())
                .build();
// 채팅방 Id값 리턴
        return ResponseDto.success(chatRoomResponseDto);
    }

    public ChatRoom getRoom(Long chatRoomId){
        return chatRoomRedisRepository.findById(chatRoomId).get();
    }

    // 채팅방 인원 추가, 삭제
    public ChatRoom setUser(Long chatRoomId, String user){
        ChatRoom chatRoom = chatRoomRedisRepository.findById(chatRoomId).get();
        List<String> users = chatRoom.getUsers();
        users.add(user);

        return chatRoom;
    }
}