package com.moa.moabackend.controller;


import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.friend.FriendRequestDto;
import com.moa.moabackend.entity.friend.FriendResponseDto;
import com.moa.moabackend.security.user.UserDetailsImpl;
import com.moa.moabackend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 추가
    @PostMapping("/friends")  // URL변경 : /add삭제
    public ResponseDto<String> addFriend(@RequestBody FriendRequestDto friendRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return friendService.addFriend(userDetails.getUser(), friendRequestDto);
    }

    // 내 친구 목록 중 친구 목록 조회
    @GetMapping("/friends")
    public ResponseDto<List<FriendResponseDto.GetMyFriendResDto>> getMyFriend(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return friendService.getMyFriend(userDetails.getUser());
    }

    // 친구 삭제
    @DeleteMapping("/friends/{friendId}")
    public ResponseDto<String> deleteFriend(@PathVariable Long friendId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return friendService.deleteFriend(userDetails.getUser(), friendId);
    }
}
