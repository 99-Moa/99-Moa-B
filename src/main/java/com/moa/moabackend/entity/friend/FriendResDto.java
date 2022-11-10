package com.moa.moabackend.entity.friend;

import com.moa.moabackend.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
//@Builder
@RequiredArgsConstructor
public class FriendResDto {

    private String friendUsername;

    private String imgUrl;

    public FriendResDto(User user) {
        this.friendUsername = user.getUserName();
        this.imgUrl = user.getImgUrl();
    }
}
