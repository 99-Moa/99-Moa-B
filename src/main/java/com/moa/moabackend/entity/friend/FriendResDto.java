package com.moa.moabackend.entity.friend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class FriendResDto {
    private String friendUsername;

    private String imgUrl;
}
