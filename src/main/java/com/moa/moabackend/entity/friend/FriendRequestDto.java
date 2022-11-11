package com.moa.moabackend.entity.friend;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendRequestDto {
    //친구 닉네임
    private String userName;
}
