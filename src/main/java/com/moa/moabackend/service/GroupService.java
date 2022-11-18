package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.group.GroupResponseDto;
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

    // 그룹 생성
    public ResponseDto<String> addGroup(GroupRequestDto requestDto) {
        int userNum = requestDto.getUserList().size();
        Group group = Group.builder()
                .userList(requestDto.getUserList())
                .groupName(requestDto.getGroupName())
                .userNum(userNum)
                .build();
        groupRepository.save(group);
        return ResponseDto.success("친구 그룹 등록 성공");
    }

    // 그룹 목록 조회
    public ResponseDto<List<GroupResponseDto>> getAllGroups() {
        List<GroupResponseDto> groupResponseLIst = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            groupResponseLIst.add(
                    GroupResponseDto.builder()
                            .userList(group.getUserList())
                            .groupName(group.getGroupName())
                            .userNum(group.getUserNum())
                            .build()
            );
        }
        return ResponseDto.success(groupResponseLIst);
    }

    // 그룹 하나 조회
    public ResponseDto<GroupResponseDto> getOneGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return ResponseDto.success(
                GroupResponseDto.builder()
                        .groupName(group.getGroupName())
                        .userList(group.getUserList())
                        .userNum(group.getUserNum())
                        .build()
        );
    }
}
