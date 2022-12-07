package com.moa.moabackend.chat.service;


import com.moa.moabackend.chat.entity.*;
import com.moa.moabackend.chat.repository.ChatRoomRedisRepository;
//import com.moa.moabackend.chat.repository.ChatRoomRepository;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.moa.moabackend.chat.entity.Status.JOIN;
import static com.moa.moabackend.chat.entity.Status.LEAVE;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ChatRoomRedisRepository chatRoomRedisRepository;
    // private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;

    // 채팅방 생성
    public ResponseDto<ChatRoomResponseDto> createRoom(ChatRoomRequestDto chatRoomRequestDto, User user) {
        System.out.println("33333333333333333333333333333333333333333333333333333333333333333333");

        Long chatRoomId = chatRoomRequestDto.getChatRoomId();

        System.out.println("444444444444444444444444444444444444444444444444444444444444444444444");


// 채팅방 없을시 저장
        if(chatRoomRedisRepository.findById(chatRoomId).isEmpty()){
            System.out.println("555555555555555555555555555555555555555555555555555555555555555555555");

// 초기값 생성, 초기값 없을시 NullpointException

            List<String> users = new ArrayList<>();
            users.add("chatRoomId"+chatRoomId);
            users.add(user.getUserName());
            System.out.println("77777777777777777777777777777777777777777777777777777777777777777777");

            ChatRoom chatRoom = ChatRoom.builder()
                    .id(chatRoomRequestDto.getChatRoomId())
                    .users(users)
                    .build();

            chatRoomRedisRepository.save(chatRoom);
        }
        System.out.println("8888888888888888888888888888888888888888888888888888888888888888888");

        ChatRoom chatRoomRepo = chatRoomRedisRepository.findById(chatRoomId).get();
        ChatRoomResponseDto chatRoomResponseDto = ChatRoomResponseDto.builder()
                .chatRoomId(chatRoomRepo.getId())
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
        System.out.println("chatRoomId : " +chatRoomId);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        ChatRoom chatRoom = chatRoomRedisRepository.findById(chatRoomId).get();
//null값 예외처리추가
        Status status = socketMessage.getStatus();
        System.out.println("status : " +status);

// token 으로 userId 추출 -----> userId 로 닉네임 추출
        String userId = jwtUtil.getUserIdFromToken(socketMessage.getToken());
        System.out.println(userId);
        String userName = userService.getUserNameByUserId(userId);
        System.out.println("userName : " + userName);
        List<String> userList = chatRoom.getUsers();
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println(userList);
//        System.out.println(userList.size());
        // 초기값 생성, 초기값 없을시 NullpointException
//        if(userList == null) {
//            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//            List<String> users = new ArrayList<>();
//            users.add(userName);
//            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
//        }

        if (status.equals(JOIN) && !(userList.contains(userName))) {
            userList.add(userName);
            System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");

        } else if (status.equals(LEAVE) && userList.contains(userName)) {
            userList.remove(userName);
            System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");

        }
        chatRoom.setUsers(userList);
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        chatRoomRedisRepository.save(chatRoom);

        return chatRoom;
    }

}