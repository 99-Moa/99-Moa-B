package com.moa.moabackend.chat.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompController {
    private final ChatRoomService chatRoomService;

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

// @Autowired
// private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // /app/message 로 받으면
// @SendTo("/topic/message") // return 값을 /topic/message 로 넘겨준다.
// public Message receiveMessage(Message message){
// /topic/chatRoomId/message
    public Message receiveMessage(@Payload Message message) {
        System.out.println("StompController message.getStatus() : " + message.getStatus());
        System.out.println("StompController message.getMessage() : " + message.getMessage());
        System.out.println("StompController message.getRoomId() : " + message.getChatRoomId());
        Long chatRoomId = message.getChatRoomId();
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/message", message);

        return message;
    }

    @MessageMapping("/plan")
    public Plan receivePlan(@Payload Plan plan) {
        System.out.println("StompController message.getStatus() : " + plan.getStartDate());
        System.out.println("StompController message.getMessage() : " + plan.getEndDate());
        System.out.println("StompController message.getRoomId() : " + plan.getTitle());
        System.out.println("StompController message.getRoomId() : " + plan.getStartTime());
        System.out.println("StompController message.getRoomId() : " + plan.getEndTime());
        System.out.println("StompController message.getRoomId() : " + plan.getLocation());
        System.out.println("StompController message.getRoomId() : " + plan.getLocationRoadName());
        System.out.println("StompController message.getRoomId() : " + plan.getContent());
        System.out.println("StompController message.getRoomId() : " + plan.getChatRoomId());
        Long chatRoomId = plan.getChatRoomId();
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/plan", plan);

        return plan;
    }

    @MessageMapping("/user")
    public ChatRoom receiveUser(@Payload Message message) {
//        System.out.println(principal.getName());
//        System.out.println(principal.getClass());
        Long chatRoomId = message.getChatRoomId();
        ChatRoom chatRoom = chatRoomService.setUser(chatRoomId, "윤상민");
        simpMessageSendingOperations.convertAndSend("/topic/" + chatRoomId + "/message", chatRoom);

        return chatRoom;
    }
}