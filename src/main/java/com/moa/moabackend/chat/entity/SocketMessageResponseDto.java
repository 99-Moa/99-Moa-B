package com.moa.moabackend.chat.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@Builder
public class SocketMessageResponseDto {

    private Long chatRoomId; //FE에서 전달
    private String sender; // token에서 발췌
    private String message; //FE에서 전달
    private ZonedDateTime time; //BE에서 생성
}
