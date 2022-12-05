package com.moa.moabackend.chat.controller;


import com.moa.moabackend.chat.entity.SocketMessageResponseDto;
import com.moa.moabackend.chat.service.ChatMessageService;
import com.moa.moabackend.entity.ResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;


    // 이전 메세지 전송
    @GetMapping("/message/{chatRoomId}")
    public ResponseDto<List<SocketMessageResponseDto>> getMessages(@PathVariable Long chatRoomId){
     return chatMessageService.getMessages(chatRoomId);
    }
}
