package com.moa.moabackend.controller;


import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.friend.FriendReqDto;
import com.moa.moabackend.entity.friend.FriendResDto;
import com.moa.moabackend.service.FriendService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 질문!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Req, Res Dto Api 요청별로 나눌지??? 아니면 하나로 돌려쓸지???
    // UserDetailsImpl 필요
    // S3는 시원님 코드로
    // friend에 friendId가 필요한지?? 그냥 닉네임으로만 저장하면 안되는지???
    // 친구 추가  API에서 request 에 userName -> friendUserName 으로 변경??
    // ResponseDto 에서 message 랑 detail 차이??
    // 이미 추가된 친구 제외?? or 프론트 단에서 처리??? 아예 리턴을 안하는걸로
    // // 전체 유저 중 친구 찾기는 UserController로 가는것이 어떤지???????
    // 친구 삭제 api 어디갓음??

    // 친구 추가
    // input : "userName": “닉네임”
    @PostMapping("friends/add")
    public ResponseDto<?> addFriend(@RequestBody FriendReqDto friendReqDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseDto.success(friendService.addFriend(userDetails.getAccount(), friendReqDto));
    }

    // 전체 유저 중 친구 찾기
    @PostMapping("/friends/search")
    public ResponseDto<FriendResDto> searchFriend(@RequestBody FriendReqDto friendReqDto,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseDto.success(friendService.searchFriend(userDetails.getAccount(), friendReqDto));
    }

    // 내 친구 목록 중 친구 목록 조회
    @GetMapping("/friends")
    public ResponseDto<List<FriendResDto>> getMyFriend(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseDto.success(friendService.getMyFriend(userDetails.getAccount());
    }


    // 친구 그룹 조회
    @GetMapping("/friends/group")
    public



    // 친구 삭제
    @DeleteMapping

}