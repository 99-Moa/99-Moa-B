package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.group.GroupAddRequestDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.group.GroupResponseDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.GroupRepository;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    // 그룹 생성
    public ResponseDto<String> addGroup(GroupRequestDto requestDto, User user) {
        List<String> userList = new ArrayList<>();
        userList.add(user.getUserName());
        for (int i = 0; i <= requestDto.getUsers().size() - 1; i++) {
            userList.add(requestDto.getUsers().get(i));
        }
//        userList.add(requestDto.getUserList().toString());
        int userNum = userList.size();
        Group group = Group.builder()
                .users(userList)
                .groupName(requestDto.getGroupName())
                .userNum(userNum)
                .build();
        groupRepository.save(group);
        return ResponseDto.success("친구 그룹 등록 성공");
    }

    // 그룹 목록 조회
    public ResponseDto<List<GroupResponseDto.groupList>> getAllGroups(User user) {
        List<GroupResponseDto.groupList> groupResponseList = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();


        for (Group group : groups) {
            List<String> imgList = new ArrayList<>();
            for (int i = 0; i <= group.getUsers().size() - 1; i++) {
                User findUser = userRepository.findByUserName(group.getUsers().get(i)).get();
                imgList.add(findUser.getImgUrl());
                if (group.getUsers().get(i).equals(user.getUserName())) {
                    if (group.getSchedule() == null) {
                        groupResponseList.add(
                                GroupResponseDto.groupList.builder()
                                        .groupId(group.getId())
                                        .groupName(group.getGroupName())
                                        .userNum(group.getUserNum())
                                        .startDate(null)
                                        .startTime(null)
                                        .location(null)
                                        .imgUrls(imgList)
                                        .build()
                        );
                    } else {
                        groupResponseList.add(
                                GroupResponseDto.groupList.builder()
                                        .groupId(group.getId())
                                        .groupName(group.getGroupName())
                                        .userNum(group.getUserNum())
                                        .startDate(group.getSchedule().getStartDate())
                                        .startTime(group.getSchedule().getStartTime())
                                        .location(group.getSchedule().getLocation())
                                        .imgUrls(imgList)
                                        .build()
                        );
                    }
                }

            }
        }


        return ResponseDto.success(groupResponseList);
    }

    // 그룹 하나 조회
    public ResponseDto<GroupResponseDto.groupDetail> getOneGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return ResponseDto.success(
                GroupResponseDto.groupDetail.builder()
                        .groupId(groupId)
                        .groupName(group.getGroupName())
                        .users(group.getUsers())
                        .userNum(group.getUserNum())
                        .build()
        );
    }

    // 그룹에 친구 추가
    public ResponseDto<String> addFriendToGroup(Long groupId, GroupAddRequestDto requestDto) {
        Group group = groupRepository.findById(groupId).get();
        for(int i = 0; i <= requestDto.getUsers().size() - 1; i ++) {
            group.getUsers().add(requestDto.getUsers().get(i));
        }
        group.setUserNum(group.getUsers().size());
        groupRepository.save(group);
        return ResponseDto.success("그룹 친구 추가 성공");
    }
}
