package com.moa.moabackend.controller;


import com.moa.moabackend.entity.user.*;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.security.user.UserDetailsImpl;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;


    // 회원가입
    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }
        // 로그인
    @PostMapping("/signin")
    public ResponseDto<?> signin(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.signin(loginRequestDto, response);
    }
        // userID 중복확인
    @PostMapping("/userIdCheck")
    public ResponseDto<?> idCheck(@RequestBody @Valid IdCheckRequestDto idCheckRequestDto){
        return userService.idCheck(idCheckRequestDto);
    }

        //닉네임 중복체크
    @PostMapping("/userNameCheck")
    public ResponseDto<?> nameCheck(@RequestBody @Valid NameCheckRequestDto idCheckRequestDto){
        return userService.nameCheck(idCheckRequestDto);
    }

    // 토큰 재발행
    @GetMapping("/issue/token")
    public UserResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getUser().getUserId(), "Access"));
        return new UserResponseDto("Success IssuedToken", HttpStatus.OK.value());
    }



}
