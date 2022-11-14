package com.moa.moabackend.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class NameCheckRequestDto {

    @NotBlank(message="닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[.][^@$!%*#?&]{2,8}$",
            message = "닉네임은 2자 ~ 8자여야 하고 특수기호는 포함할 수 없습니다.")

    private String userName;

    public NameCheckRequestDto(String userName) {
        this.userName = userName;
    }
}