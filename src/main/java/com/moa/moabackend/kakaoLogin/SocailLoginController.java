package com.moa.moabackend.kakaoLogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moa.moabackend.entity.user.SocialUserInfoDto;
import com.moa.moabackend.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class SocailLoginController {
    private final KakaoUserService kakaoUserService;

    // 카카오 로그인
    @GetMapping("/kakao")
    public SocialUserInfoDto kakaoLogin(String code, TokenDto tokenDto, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, tokenDto, response);
    }
}