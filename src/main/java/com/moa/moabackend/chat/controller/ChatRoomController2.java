//package com.moa.moabackend.chat.controller;
//
//import com.moa.moabackend.chat.entity.ChatRoomRedis;
//import com.moa.moabackend.chat.service.ChatService;
//import com.moa.moabackend.chat.LoginInfo;
//import com.moa.moabackend.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
////@RequestMapping("/socket")
//public class ChatRoomController2 {
//
//    private final ChatService chatService;
////    private final JwtTokenProvider jwtTokenProvider;
//    private final JwtUtil jwtUtil;
//
//    // 채팅방 목록 조회
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<ChatRoomRedis> room() {
//        return chatService.findAllRoom();
//    }
//
//    // 채팅방 생성
//    @PostMapping("/room")
//    @ResponseBody
//    public ChatRoomRedis createRoom(@RequestParam String name) {
//        return chatService.createRoom(name);
//    }
//
//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
//
//    // 특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoomRedis roomInfo(@PathVariable String roomId) {
//        return chatService.findById(roomId);
//    }
//
//    // 로그인한 회원은 id 및 jwt 토큰 정보 조회
//    @GetMapping("/user")
//    @ResponseBody
//    public LoginInfo getUserInfo() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        return LoginInfo.builder()
//                .name(name)
////                .token(jwtTokenProvider.generateToken(name))
//                .build();
//    }
//
//
//}
