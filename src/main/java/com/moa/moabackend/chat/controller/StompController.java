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
        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");

        SocketMessage chatMessage = chatMessageService.getMessage(socketMessageRequsetDto);
        System.out.println("ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");

<<<<<<< HEAD
        // /topic/chatRoomId/message
=======
// /topic/chatRoomId/message
>>>>>>> 55e2bd3ef140e4728daef3a46f7a1192d39f76e4
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/message", chatMessage);
    }

    @MessageMapping("/plan")
    public void receivePlan(@Payload SocketPlan socketPlan) {
        Long chatRoomId = socketPlan.getChatRoomId();
// /topic/chatRoomId/plan
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/plan", socketPlan);
    }

    @MessageMapping("/user")
    public void receiveUser(@Payload SocketMessage socketMessage) {
        Long chatRoomId = socketMessage.getChatRoomId();
        System.out.println("99999999999999999999999999999999999999999999999999999999999999999999999999");

        ChatRoom chatRoom = chatRoomService.setUser(chatRoomId, socketMessage);
        System.out.println("10101010101010101010101010101010101010101010101010101010101010101010101010101010101010");

<<<<<<< HEAD
        // /topic/chatRoomId/user
=======
// /topic/chatRoomId/user
>>>>>>> 55e2bd3ef140e4728daef3a46f7a1192d39f76e4
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