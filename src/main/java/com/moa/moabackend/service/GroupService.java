package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.group.GroupAddRequestDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.group.GroupResponseDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.GroupRepository;
import com.moa.moabackend.repository.UserRepository;
import com.moa.moabackend.sse.Alert;
import com.moa.moabackend.sse.AlertRepository;
import com.moa.moabackend.sse.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final AlertService alertService;

    // 그룹 생성 + 알람저장
    public ResponseDto<String> addGroup(GroupRequestDto requestDto, User user) {
        List<String> userList = new ArrayList<>();
        userList.add(user.getUserName());
        for (int i = 0; i <= requestDto.getUsers().size() - 1; i++) {
            userList.add(requestDto.getUsers().get(i));
            // 알람 저장
            Alert alert = Alert.builder()
                    .message(user.getUserName() + "님이 그룹에 초대했습니다.")
                    .receiver(requestDto.getUsers().get(i))
                    .check(false)
                    .build();

            User findUser = userRepository.findByUserName(requestDto.getUsers().get(i)).get();
            alertRepository.save(alert);
            alertService.alertEventAddGroup(findUser, alert);
        }
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
            List<Map<String, String>> userInfoList = new ArrayList<>();
            for (int i = 0; i <= group.getUsers().size() - 1; i++) {
                Map<String, String> userMap = new HashMap<String, String>();
                User findUser = userRepository.findByUserName(group.getUsers().get(i)).get();
                userMap.put("userName", findUser.getUserName());
                userMap.put("imgUrl", findUser.getImgUrl());
                userInfoList.add(userMap);
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
                                        .userInfoList(userInfoList)
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
                                        .userInfoList(userInfoList)
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
        List<Long> userIdList = new ArrayList<>();
        for (int i = 0; i <= group.getUsers().size() - 1; i++) {
            User user = userRepository.findByUserName(group.getUsers().get(i)).get();
            userIdList.add(user.getId());
        }
        return ResponseDto.success(
                GroupResponseDto.groupDetail.builder()
                        .groupId(groupId)
                        .groupName(group.getGroupName())
                        .userIdList(userIdList)
                        .userNum(group.getUserNum())
                        .build()
        );
    }

    // 그룹에 친구 추가
    public ResponseDto<String> addFriendToGroup(Long groupId, GroupAddRequestDto requestDto) {
        Group group = groupRepository.findById(groupId).get();
        for (int i = 0; i <= requestDto.getUsers().size() - 1; i++) {
            group.getUsers().add(requestDto.getUsers().get(i));
        }
        group.setUserNum(group.getUsers().size());
        groupRepository.save(group);
        return ResponseDto.success("그룹 친구 추가 성공");
    }
}
