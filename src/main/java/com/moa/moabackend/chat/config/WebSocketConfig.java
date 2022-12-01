//package com.moa.moabackend.chat.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//// private final ReplyEchoHandler replyEchoHandler;
//
//    @Autowired
//    private ReplyEchoHandler replyEchoHandler;
//
//    //WebSocketHandlerRegistry에 WebSocketHandler의 구현체를 등록한다.
//// 등록된 Handler는 특정 endpoint("/wa/chat")로 handshake를 완료한 후 맺어진 connection의 관리한다.
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//// 해당 endpoint로 handshake가 이루어진다.
//        registry.addHandler(replyEchoHandler, "/chatroom")
//                .setAllowedOrigins("*");
//// .withSockJS();
//    }
//}