package com.moa.moabackend.entity.user;


import com.moa.moabackend.entity.Timestamped;
import com.moa.moabackend.entity.user.mypage.MypageRequestDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "users")  // table name 은 'user' 가 아닌 'users' 로 변경
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
//    @NotBlank
//    @Column
    private String userId;
//    @NotBlank
//    @Column
    private String password;
//    @NotBlank
//    @Column
    private String passwordCheck;
//    @NotBlank
//    @Column
    private String userName;

//    @Column
    private String imgUrl = "https://yuns8708bucket.s3.ap-northeast-2.amazonaws.com/images/moa99_profile.jpg";

    public User(UserRequestDto userRequestDto) {
        this.userId = userRequestDto.getUserId();
        this.password = userRequestDto.getPassword();
        this.passwordCheck = userRequestDto.getPasswordCheck();
        this.userName = userRequestDto.getUserName();
    }

    public User(String kakaouserId, String password, String passwordCheck) {
        this.userId = kakaouserId;
        this.userName = kakaouserId;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }


    // 프로필 수정 메소드
    public void updateUser(MypageRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.imgUrl = requestDto.getImgUrl();
    }
}
