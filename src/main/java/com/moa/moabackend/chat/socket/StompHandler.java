//package com.moa.moabackend.chat.socket;
//
//import com.moa.moabackend.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.messaging.SocketMessage;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class StompHandler implements ChannelInterceptor {
//
////    private final JwtTokenProvider jwtTokenProvider;
//    private final JwtUtil jwtUtil;
//
//    // websocket을 통해 들어온 요청이 처리 되기전 실행된다. interceptor.
//    // 유효하지 않은 jwt 토큰일 경우 websocket을 연결하지 않고 예외처리.
//    @Override
//    public SocketMessage<?> preSend(SocketMessage<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        // websocket 연결시 헤더의 jwt token 검증
//        if (StompCommand.CONNECT == accessor.getCommand()) {
////            jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("token"));
//        }
//        return message;
//    }
//}