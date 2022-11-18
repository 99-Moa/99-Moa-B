package com.moa.moabackend.entity.user;


import com.moa.moabackend.entity.Timestamped;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "users")  // table name 은 'user' 가 아닌 'users' 로 변경
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String userName;

    private String imgUrl = "anonymous";

    public User(UserRequestDto userRequestDto) {
        this.userId = userRequestDto.getUserId();
        this.password = userRequestDto.getPassword();
        this.passwordCheck = userRequestDto.getPasswordCheck();
        this.userName = userRequestDto.getUserName();
    }


    // 프로필 수정 메소드
    public void updateUser(MypageRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.imgUrl = requestDto.getImgUrl();
    }
}
