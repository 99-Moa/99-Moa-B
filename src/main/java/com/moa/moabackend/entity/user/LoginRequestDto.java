package com.moa.moabackend.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    private String userId;
    @NotBlank
    private String password;

    public LoginRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}