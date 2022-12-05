package com.moa.moabackend.chat.service;

import com.moa.moabackend.chat.entity.SocketMessage;
import com.moa.moabackend.chat.entity.SocketMessageRequsetDto;
import com.moa.moabackend.chat.entity.SocketMessageResponseDto;
import com.moa.moabackend.chat.repository.ChatMessageRepository;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final JwtUtil jwtUtil;
    private final ChatMessageRepository chatMessageRepository;
    private UserService userService;

    // 메세지 저장
    public SocketMessage getMessage(SocketMessageRequsetDto socketMessageRequsetDto){
        // sender 추가
        String userId = jwtUtil.getUserIdFromToken(socketMessageRequsetDto.getToken());
        String userName = userService.getUserNameByUserId(userId);

        // time 추가
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        SocketMessage socketMessage = SocketMessage.builder()
                .chatRoomId(socketMessageRequsetDto.getChatRoomId())
                .sender(userName)
                .time(time)
                .message(socketMessageRequsetDto.getMessage())
                .build();

        chatMessageRepository.save(socketMessage);

        return socketMessage;
    }

    // 이전 메세지 전송
    public ResponseDto<List<SocketMessageResponseDto>> getMessages(Long chatRoomId){
        List<SocketMessage> socketMessageList = chatMessageRepository.findAllByChatRoomId(chatRoomId);

        List<SocketMessageResponseDto> socketMessageResponseDtoList = new ArrayList<>();

        for (SocketMessage socketMessage : socketMessageList){
            socketMessageResponseDtoList.add(SocketMessageResponseDto.builder()
                            .sender(socketMessage.getSender())
                            .message(socketMessage.getMessage())
                            .time(socketMessage.getTime())
                            .build()
            );
        }
        return ResponseDto.success(socketMessageResponseDtoList);
    }
}
