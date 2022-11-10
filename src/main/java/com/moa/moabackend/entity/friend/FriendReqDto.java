package com.moa.moabackend.entity.friend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendReqDto {
    //친구 닉네임
    private String userName;
}
