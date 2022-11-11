package com.moa.moabackend.controller;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.user.IdCheckRequestDto;
import com.moa.moabackend.entity.user.NameCheckRequestDto;
import com.moa.moabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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
}
