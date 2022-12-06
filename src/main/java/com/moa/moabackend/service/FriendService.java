package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.friend.Friend;
import com.moa.moabackend.entity.friend.FriendRequestDto;
import com.moa.moabackend.entity.friend.FriendResponseDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.FriendRepository;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 친구 추가
    public ResponseDto<String> addFriend(User user, FriendRequestDto friendRequestDto) {

        String friendUsername = friendRequestDto.getUserName();
        if (checkMyFriend(user.getUserId(), friendUsername)) {
            return ResponseDto.fail(400, "Bad Request", "이미 등록된 친구입니다.");
        }
        if(userRepository.findByUserName(friendUsername).isPresent()){
            Friend friend = Friend.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .friendUsername(friendUsername)
                    .build();
            friendRepository.save(friend);
            return ResponseDto.success("친구 추가 완료");
        }else{
            // 친구존재하지 않을 경우
            return ResponseDto.fail(404, "Bad Request", "해당하는 친구가 없습니다.");
        }
    }

    // 내 친구 목록 중 친구 목록 조회
    public ResponseDto<List<FriendResponseDto.GetMyFriendResDto>> getMyFriend(User user) {

        String userId = user.getUserId();
        List<Friend> friendList = friendRepository.findAllByUserId(userId);
        List<FriendResponseDto.GetMyFriendResDto> friendListResDto = new ArrayList<>();
        for (Friend friend : friendList) {
            //Null 예외처리 추가
            User userFriend = userRepository.findByUserName(friend.getFriendUsername()).orElseThrow();
            friendListResDto.add(
                    FriendResponseDto.GetMyFriendResDto.builder()
                            .id(friend.getId())
                            .userId(userFriend.getUserId())
                            .friendUsername(userFriend.getUserName())
                            .imgUrl(userFriend.getImgUrl())
                            .build());
        }
        return ResponseDto.success(friendListResDto);
    }

    // 친구 삭제
    public ResponseDto<String> deleteFriend(User user, Long friendId) {
        // 자기 친구만 삭제 가능
        // id에 해당하는 friend 없을 경우 Null 예외처리 추가
        String userId = friendRepository.findById(friendId).get().getUserId();
        if(userId.equals(user.getUserId())){
            friendRepository.deleteById(friendId);
            return ResponseDto.success("삭제 완료");
        }else{
            return ResponseDto.fail(401,"Unauthorized","자신의 친구만 삭제할 수 있습니다.");
        }
    }

    // 내 친구인지 체크
    public boolean checkMyFriend(String userId, String friendUsername) {
        List<Friend> friendList = friendRepository.findAllByUserId(userId);
        boolean result = false;
        for (Friend friend : friendList){
            if (friend.getFriendUsername().equals(friendUsername)) {
                result = true;
                break;
            }
        }return result;
    }
}
