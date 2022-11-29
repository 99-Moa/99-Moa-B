package com.moa.moabackend.entity.user.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MypageResponseDto {
    private Long id;
    private String userName;
    private String imgUrl;
}
