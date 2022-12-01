package com.moa.moabackend.chat.service;


import com.moa.moabackend.chat.entity.*;
import com.moa.moabackend.chat.repository.ChatRoomRedisRepository;
//import com.moa.moabackend.chat.repository.ChatRoomRepository;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.moa.moabackend.chat.entity.Status.JOIN;
import static com.moa.moabackend.chat.entity.Status.LEAVE;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ChatRoomRedisRepository chatRoomRedisRepository;
    //    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;

    // 채팅방 생성
    public ResponseDto<ChatRoomResponseDto> createRoom(ChatRoomRequestDto chatRoomRequestDto) {
        List<String> users = new ArrayList<>();
        users.add("이동진");

        // 채팅방 저장
        ChatRoom chatRoom = ChatRoom.builder()
                .id(chatRoomRequestDto.getChatRoomId())
                .users(users)
                .build();

        chatRoomRedisRepository.save(chatRoom);

        ChatRoomResponseDto chatRoomResponseDto = ChatRoomResponseDto.builder()
                .chatRoomId(chatRoom.getId())
                .build();

        // 채팅방 Id값 리턴
        return ResponseDto.success(chatRoomResponseDto);
    }

    // 채팅방 단일 조회
    public ChatRoom getRoom(Long chatRoomId) {
        return chatRoomRedisRepository.findById(chatRoomId).get();
    }

    // 채팅방 인원 추가, 삭제
    public ChatRoom setUser(Long chatRoomId, SocketMessage socketMessage) {
        System.out.println("111111111111111111111111111111111111111111");
        ChatRoom chatRoom = chatRoomRedisRepository.findById(chatRoomId).get();
        System.out.println("2222222222222222222222222222222222222222222222");
        //null값 예외처리추가
        Status status = socketMessage.getStatus();
        System.out.println("33333333333333333333333333333333333333333");

        // token 으로 userId 추출  -----> userId 로 닉네임 추출
        String userId = jwtUtil.getUserIdFromToken(socketMessage.getToken());
        System.out.println("44444444444444444444444444444444444444444444");

        String userName = userService.getUserNameByUserId(userId);
        System.out.println("5555555555555555555555555555555555555555555");

        List<String> userList = chatRoom.getUsers();
        System.out.println("666666666666666666666666666666666666666666666");

        if (status.equals(JOIN) && !(userList.contains(userName))) {
            userList.add(userName);
            System.out.println("77777777777777777777777777777777777777777777777");

        } else if (status.equals(LEAVE) && userList.contains(userName)) {
            userList.remove(userName);
            System.out.println("888888888888888888888888888888888888888888");

        }
        chatRoom.setUsers(userList);
        System.out.println("999999999999999999999999999999999999999999999");

        chatRoomRedisRepository.save(chatRoom);
        System.out.println("101010101010101010101010101010101010101010101010101010");

        return chatRoom;
    }

}


