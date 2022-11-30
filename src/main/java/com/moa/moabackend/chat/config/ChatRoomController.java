package com.moa.moabackend.chat.config;

import com.moa.moabackend.entity.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ResponseDto<ChatRoomResponseDto> createRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        return chatRoomService.createRoom(chatRoomRequestDto);
    }
}