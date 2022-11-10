package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.friend.Friend;
import com.moa.moabackend.entity.friend.FriendReqDto;
import com.moa.moabackend.entity.friend.FriendResDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.FriendRepository;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 친구 추가
    public String addFriend(User user, FriendReqDto friendReqDto){
        User user1 = userRepository.findByUserName(user.getUserName()).orElseThrow();
        // 친구닉네임이 존재하는지 체크 추가
        String userName = friendReqDto.getUserName();
        if(userRepository.findByUserName(userName).isPresent()){
            friendRepository.save(new Friend(user1.getUserId(), user1.getUserName(), userName));
            return "친구 추가 성공";
        }else{
            // 자기 자신을 추가할 경우 추가!
            // 친구존재하지 않을 경우
            return "해당하는 친구가 없습니다.";
        }
    }

    // 친구 찾기
    public FriendResDto.SearchFriendResDto searchFriend(User user, FriendReqDto friendReqDto){
        // 내 정보
//        User user = userRepository.findByUserName(user.getUserName()).orElseThrow();
        // 친구 정보
        // 친구에 해당하는 유저 없을 시 예외처리 추가
        User userFriend = userRepository.findByUserName(friendReqDto.getUserName()).orElseThrow();
        return new FriendResDto.SearchFriendResDto(userFriend);
        // 이미 추가된 친구 안보이기 제외 추가??
    }

    // 내 친구 목록 중 친구 목록 조회
    public List<FriendResDto.GetMyFriendResDto> getMyFriend(User user){
//        System.out.println(user.getUserId());
//        System.out.println(user.getUserName());
//        User user1 = userRepository.findByUserName(user.getUserName()).orElseThrow();
//        System.out.println(user1.getUserId());
//        System.out.println(user1.getUserName());
        String userId = user.getUserId();

        List<Friend> friendList = friendRepository.findAllByUserId(userId);
        List<FriendResDto.GetMyFriendResDto> friendListResDto = new ArrayList<>();
        for(Friend friend : friendList){
            //Null 예외처리 추가
            User user2 = userRepository.findByUserName(friend.getFriendUsername()).orElseThrow();
            friendListResDto.add(
                    new FriendResDto.GetMyFriendResDto(friend.getId(), user2)
//                    FriendResDto.builder()
//                            .friendUsername(friend.getFriendUsername())
//                            .imgUrl(friend.get)
//                            .build()
            );
        }
            return friendListResDto;
    }

    // 친구 삭제
    public String deleteFriend(User user, Long frienId){
        // 자기 친구(userId 일치하는경우)만 삭제 가능하게 로직 구성 추가
//        String userId = user.getUserId();
//        System.out.println(userName);
//        friendRepository.deleteByFriendUsername(friendReqDto.getUserName());
//        List<Friend> friendList = friendRepository.findAllByUserId(userId);
//        friendRepository.deleteByUserIdAndFriendUsername(userId, userName);
        friendRepository.deleteById(frienId);

        return "삭제 완료";
    }
}
