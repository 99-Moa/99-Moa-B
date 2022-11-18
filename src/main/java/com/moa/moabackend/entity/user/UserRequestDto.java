package com.moa.moabackend.entity.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message="아이디는 필수 입력사항입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$",
            message = "아이디는 4자 ~ 16자의 영어와 숫자여야 합니다.")
    private String userId;

    @NotBlank(message="닉네임은 필수 입력사항입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣][^@$!%*#?&]{1,7}$",
            message = "닉네임은 2자 ~ 8자여야 하고 특수기호는 포함할 수 없습니다.")
    private String userName;

    @NotBlank(message="패스워드는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank
    private String passwordCheck;

    public UserRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserRequestDto(String userId, String password, String passwordCheck, String userName) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.userName = userName;
    }

    // 인코딩된 패스워드 값을 password 에 입력
    public void setEncodePwd(String encodePwd) {
        this.password = encodePwd;
    }

}
