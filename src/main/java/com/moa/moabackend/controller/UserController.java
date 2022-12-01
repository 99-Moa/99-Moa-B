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

    private final UserService userService;
    private final JwtUtil jwtUtil;


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

    // 로그아웃
    @PostMapping("/signout")
    public ResponseDto<String> signout(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.signout(userDetails.getUser().getUserId());
    }

    // 토큰 재발행
    @GetMapping("/issue/token")
    public ResponseDto<String> issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        return userService.issuedToken(userDetails.getUser().getUserId(), response);
    }

    // 전체 유저 중 친구 찾기
    @GetMapping ("/users/{userName}")  // URL변경필요 : GET /users
    public ResponseDto<FriendResponseDto.SearchFriendResDto> searchFriend(@PathVariable String userName, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.searchFriend(userDetails.getUser(), userName);
    }
}
