package com.moa.moabackend.controller;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.group.GroupResponseDto;
import com.moa.moabackend.entity.schedule.ScheduleRequestDto;
import com.moa.moabackend.security.user.UserDetailsImpl;
import com.moa.moabackend.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    // 친구 그룹 생성
    @PostMapping("/group")
    public ResponseDto<String> addGroup(@RequestBody GroupRequestDto requestDto) {
        return groupService.addGroup(requestDto);
    }
    
    // 친구 그룹 조회
    @GetMapping("/group")
    public ResponseDto<List<GroupResponseDto>> getAllGroups() {
        return groupService.getAllGroups();
    }
}
