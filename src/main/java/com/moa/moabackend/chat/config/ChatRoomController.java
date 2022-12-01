package com.moa.moabackend.chat.config;

import com.moa.moabackend.entity.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ResponseDto<ChatRoomResponseDto> createRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto){
        return chatRoomService.createRoom(chatRoomRequestDto);
    }

    @GetMapping("/room/{chatRoomId}")
    @ResponseBody
    public ChatRoom getRoom(@PathVariable Long chatRoomId){
        return chatRoomService.getRoom(chatRoomId);
    }
}