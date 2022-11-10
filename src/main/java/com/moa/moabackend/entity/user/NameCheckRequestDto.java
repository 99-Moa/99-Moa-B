package com.moa.moabackend.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class NameCheckRequestDto {

    @NotBlank(message="닉네임은 필수 입력값입니다.")

    private String userName;

    public NameCheckRequestDto(String userName) {
        this.userName = userName;
    }
}