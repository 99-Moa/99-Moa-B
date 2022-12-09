package com.moa.moabackend.chat.controller;

import com.moa.moabackend.chat.entity.*;
import com.moa.moabackend.chat.service.ChatMessageService;
import com.moa.moabackend.chat.service.ChatRoomService;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
@RequiredArgsConstructor
public class StompController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

// @Autowired
// private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // /app/socketMessage 로 받으면
// @SendTo("/topic/socketMessage") // return 값을 /topic/socketMessage 로 넘겨준다.
    public void receiveMessage(@Payload SocketMessageRequsetDto socketMessageRequsetDto) {
        Long chatRoomId = socketMessageRequsetDto.getChatRoomId();
        SocketMessage chatMessage = chatMessageService.getMessage(socketMessageRequsetDto);
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/message", chatMessage);
    }

    @MessageMapping("/plan")
    public void receivePlan(@Payload SocketPlan socketPlan) {
        Long chatRoomId = socketPlan.getChatRoomId();
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/plan", socketPlan);
    }

    @MessageMapping("/user")
    public void receiveUser(@Payload SocketMessage socketMessage) {
        Long chatRoomId = socketMessage.getChatRoomId();
        ChatRoom chatRoom = chatRoomService.setUser(chatRoomId, socketMessage);
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/user", chatRoom.getUsers());
    }
}

// @SubscribeMapping

// @MessageMapping("/private-message")
// public SocketMessage receivePrivateMessage(@Payload SocketMessage message){
// simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); // /user/David/private
// return message;
// }

// messagingTemplate.convertAndSend("/topic/" + message.getRoomid(), message.getMsg());
//// messagingTemplate.convertAndSendToUser(message.getId(), "/topic/" + message.getRoomid(), message.getMsg());
//
// return message;
// }