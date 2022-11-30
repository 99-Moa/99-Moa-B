package com.moa.moabackend.chat.config;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;


import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("chatroom")
public class ChatRoom implements Serializable {
    // redis 에 저장되는 객체는 직렬화 해야됨
// private static final long serialVersionUID = 6494678977089006639L;
    private Long id;
    // private String roomName;
    private List<String> users;

// public static ChatRoom create(String name){
// ChatRoom room = new ChatRoom();
// room.roomId = UUID.randomUUID().toString();
// room.roomName = name;
// return room;
// }
}