package com.moa.moabackend.entity.user;


import com.moa.moabackend.entity.Timestamped;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

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

    @Column(unique = true, nullable = false)
    private String kakaoEmail;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String nickname;

    @Column(nullable = false)
    private String profile;

    @Column(nullable = false)
    private String encodedPassword;


    public User(UserRequestDto userRequestDto) {
        this.userId = userRequestDto.getUserId();
        this.password = userRequestDto.getPassword();
        this.passwordCheck = userRequestDto.getPasswordCheck();
        this.userName = userRequestDto.getUserName();
    }


    public User(String kakaoEmail, String nickname, String profile, String encodedPassword) {
        this.kakaoEmail = kakaoEmail;
        this.nickname = nickname;
        this.profile = profile;
        this.encodedPassword = encodedPassword;

    }
//    kakaoEmail, nickname, profile, encodedPassword


    // 프로필 수정 메소드
    public void updateUser(MypageRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.imgUrl = requestDto.getImgUrl();
    }
}
