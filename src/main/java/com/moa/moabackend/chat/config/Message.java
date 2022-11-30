package com.moa.moabackend.chat.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Status status;
    private String sender;
    private Long chatRoomId;
    // private String receiver;
    private String message;
    private String time;

}