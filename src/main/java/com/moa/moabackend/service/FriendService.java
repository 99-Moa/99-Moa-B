package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.friend.Friend;
import com.moa.moabackend.entity.friend.FriendReqDto;
import com.moa.moabackend.entity.friend.FriendResDto;
import com.moa.moabackend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 친구 추가
    public String addFriend(User user, FriendReqDto friendReqDto){
        User user = userRepository.findByUserName(user.getUserName()).orElseThrow();
        // 친구닉네임이 존재하는지 체크 추가
        String frienduserName = friendReqDto.getUserName();
        if(userRepository.findByuserName(frienduserName).isPresent()){
            friendRepository.save(new Friend(user.getUserId(), user.getUserName(), frienduserName);
            return "친구 추가 성공";
        }else{
            // 친구존재하지 않을 경우
            return "해당하는 친구가 없습니다.";
        }
    }

    // 친구 찾기
    public UserResDto searchFriend(User user, FriendReqDto friendReqDto){
        // 내 정보
        User user = userReposirtory.findByUserName(user.getUserName()).orElseThrow();
        // 친구 정보
        User userFriend = userRepository.findByUserName(friendReqDto.getUserName()).orElseThrow();
        return new UserResDto(User userFriend);
        // 이미 추가된 친구 안보이기 제외 추가??
    }

    // 내 친구 목록 중 친구 목록 조회
    public List<FriendResDto> getMyFriend(User user){
        User user = userReposirtory.findByUserName(user.getUserName()).orElseThrow();


    }

}
