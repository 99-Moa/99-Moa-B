package com.moa.moabackend.chat.config;

import com.moa.moabackend.entity.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    // 채팅방 생성
    public ResponseDto<ChatRoomResponseDto> createRoom(ChatRoomRequestDto chatRoomRequestDto){
// 채팅방 저장
        ChatRoom chatRoom = ChatRoom.builder()
                .id(chatRoomRequestDto.getChatRoomId())
                .build();
        chatRoomRepository.save(chatRoom);
        ChatRoomResponseDto chatRoomResponseDto = ChatRoomResponseDto.builder()
                .chatRoomId(chatRoom.getId())
                .build();
// 채팅방 Id값 리턴
        return ResponseDto.success(chatRoomResponseDto);
    }
}