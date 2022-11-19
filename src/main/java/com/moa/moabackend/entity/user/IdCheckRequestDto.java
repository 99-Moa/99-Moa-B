package com.moa.moabackend.entity.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class IdCheckRequestDto {

    @NotBlank(message="아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$",
           message = "아이디는 4자 ~ 16자의 아이디여야 합니다.")
    private String userId;

    public IdCheckRequestDto(String userId) {
        this.userId = userId;
    }
}