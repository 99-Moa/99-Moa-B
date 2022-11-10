package com.moa.moabackend.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MypageRequestDto {
    private String userName;
    private String imgUrl;
}
