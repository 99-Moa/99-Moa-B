package com.moa.moabackend.chat.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocketMessage {

    private Status status; //FE에서 전달, JOIN, MESSAGE, LEAVE
    private String sender; // token에서 발췌
    private Long chatRoomId; //FE에서 전달
    //    private String receiver;
    private String message; //FE에서 전달
    private LocalDateTime time; //BE에서 생성
    private String token;
}
