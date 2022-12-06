package com.moa.moabackend.chat.controller;


import com.moa.moabackend.chat.entity.ChatRoom;
import com.moa.moabackend.chat.entity.ChatRoomRequestDto;
import com.moa.moabackend.chat.entity.ChatRoomResponseDto;
import com.moa.moabackend.chat.entity.SocketMessage;
import com.moa.moabackend.chat.service.ChatRoomService;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    @PostMapping("/room")
    public ResponseDto<ChatRoomResponseDto> createRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111");
        System.out.println(userDetails.getUser().getUserName());
        System.out.println("222222222222222222222222222222222222222222222222222222222222");
        return chatRoomService.createRoom(chatRoomRequestDto, userDetails.getUser());
    }

    // 채팅방 단일 조회
    @GetMapping("/room/{chatRoomId}")
    public ChatRoom getRoom(@PathVariable Long chatRoomId){
        return chatRoomService.getRoom(chatRoomId);
    }

    //  // 채팅방 인원 추가, 삭제
    @PostMapping("/room/person")
    public ChatRoom setUser(@RequestBody SocketMessage socketMessage){
        Long chatRoomId = socketMessage.getChatRoomId();
        System.out.println("chatRoomId : " +chatRoomId);
        return chatRoomService.setUser(chatRoomId, socketMessage);
    }
}
