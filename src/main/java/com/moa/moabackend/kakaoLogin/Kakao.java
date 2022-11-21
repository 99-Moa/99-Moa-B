package com.moa.moabackend.kakaoLogin;

import com.moa.moabackend.entity.Timestamped;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class Kakao extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true /*nullable = false*/)
    private String kakaoEmail;

    @Column(unique = true /*nullable = false*/)
    private String username;

    @Column(unique = true)
    private String nickname;

    @Column(/*nullable = false*/)
    private String profile;

    @Column(/*nullable = false*/)
    private String encodedPassword;


    public Kakao(String kakaoEmail, String nickname, String profile, String encodedPassword) {
        this.kakaoEmail = kakaoEmail;
        this.nickname = nickname;
        this.profile = profile;
        this.encodedPassword = encodedPassword;

    }
}