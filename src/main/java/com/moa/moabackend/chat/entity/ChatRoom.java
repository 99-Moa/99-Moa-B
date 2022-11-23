package com.moa.moabackend.chat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
// redis 에 저장되는 객체는 직렬화 해야됨
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;
    private String roomId;
    private String roomName;

    public static ChatRoom create(String name){
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }


}
