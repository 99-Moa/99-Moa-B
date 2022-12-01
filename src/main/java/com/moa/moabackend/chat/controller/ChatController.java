//package com.moa.moabackend.chat.controller;
//
//import com.moa.moabackend.chat.entity.ChatMessage;
//import com.moa.moabackend.chat.entity.SocketMessage;
//import com.moa.moabackend.chat.service.ChatService;
//import com.moa.moabackend.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
//
////@RestController
////@RequiredArgsConstructor
//@Controller
//public class ChatController {
//
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/message") // /app/message
//    @SendTo("/chatroom/public")
//    public SocketMessage receivePublicMessage(@Payload SocketMessage message, @AuthenticationPrincipal
//    ) {
//        return message;
//    }
//
//    @MessageMapping("/private-message")
//    public SocketMessage receivePrivateMessage(@Payload SocketMessage message){
//        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); // /user/David/private
//        return message;
//    }
//}
//
////    private final ChatService chatService;
////    private final JwtTokenProvider jwtTokenProvider;
////    private final JwtUtil jwtUtil;
////    private final RedisTemplate redisTemplate;
////    private final ChannelTopic channelTopic;
//
//    // 1. "/app/chat/message" 로 오는 요청을 처리.
//    // 2. ChatRoomController가 받아서 "/topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지 전달.
////    @MessageMapping("/chat/message")
////    public void enter(ChatMessage message, @Header("token") String token) {
////
////        // token provider로부터 user nickname 받아옴
//////        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
////
//////        message.setSender(nickname);
////
////        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
////            message.setSender("[알림]");
//////            message.setMessage(nickname+"님이 입장하였습니다.");
////        }
////        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
////    }
//
////    @MessageMapping("/socket/message")
////    public void socketmessage(ChatMessage message) {
////        System.out.println(message);
//
//
////        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
////            message.setSender("[알림]");
//////            message.setMessage(nickname+"님이 입장하였습니다.");
////        }
////        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
////    }
//
//
//
//    // redis를 쓰지 않을 때는 SimpleMessageSendingOperations를 사용해서 메시지를 도착지까지 전송.
//    // redis를 썼을 떄는 WebSocket에 발행된 메시지를 redis로 발행(publish)
////}
