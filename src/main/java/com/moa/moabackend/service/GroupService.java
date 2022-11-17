package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.group.GroupResponseDto;
import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.schedule.ScheduleRequestDto;
import com.moa.moabackend.entity.schedule.ScheduleResponseDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.GroupRepository;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    // 그룹 생성
    public ResponseDto<String> addGroup(GroupRequestDto requestDto) {
        User user = userRepository.findById(1L).orElseThrow();
        List<String> userList = new ArrayList<>();
        userList.add(user.getUserName());
        userList.add(requestDto.getUserName().toString());
        Group group = Group.builder()
//                .userName(requestDto.getUserName())
                .userList(userList)
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
                            .userName(group.getUserName())
                            .userList(group.getUserList())
                            .build()
            );
        }
        return ResponseDto.success(groupResponseLIst);
    }
}
