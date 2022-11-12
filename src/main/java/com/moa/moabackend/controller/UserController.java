package com.moa.moabackend.controller;


import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.friend.FriendRequestDto;
import com.moa.moabackend.entity.friend.FriendResponseDto;
import com.moa.moabackend.entity.user.*;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.security.user.UserDetailsImpl;
import com.moa.moabackend.service.FriendService;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
//pppppptest//
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final FriendService friendService;


    // 회원가입
    @PostMapping("/signup")
    public ResponseDto<String> signup(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }
        // 로그인
    @PostMapping("/signin")
    public ResponseDto<String> signin(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.signin(loginRequestDto, response);
    }

    // 토큰 재발행
    @GetMapping("/issue/token")
    public UserResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getUser().getUserId(), "Access"));
        return new UserResponseDto("Success IssuedToken", HttpStatus.OK.value());
    }

    // 전체 유저 중 친구 찾기
    @GetMapping ("/users/{userName}")  // URL변경필요 : GET /users
    public ResponseDto<FriendResponseDto.SearchFriendResDto> searchFriend(@PathVariable String userName, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return friendService.searchFriend(userDetails.getUser(), userName);
    }
}
