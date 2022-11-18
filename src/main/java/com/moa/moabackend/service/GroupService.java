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

    // 그룹 조회
    public ResponseDto<List<GroupResponseDto>> getAllGroups() {
        List<GroupResponseDto> groupResponseLIst = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            groupResponseLIst.add(
                    GroupResponseDto.builder()
                            .userList(group.getUserList())
                            .groupName(group.getGroupName())
                            .build()
            );
        }
        return ResponseDto.success(groupResponseLIst);
    }
}
