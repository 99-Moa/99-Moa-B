package com.moa.moabackend.controller;

import com.moa.moabackend.chat.service.ChatRoomService;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.GroupAddRequestDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.group.GroupResponseDto;
import com.moa.moabackend.entity.schedule.ScheduleRequestDto;
import com.moa.moabackend.security.user.UserDetailsImpl;
import com.moa.moabackend.service.GroupService;
import com.moa.moabackend.sse.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final ChatRoomService chatRoomService;
    private final AlertService alertService;

    // 친구 그룹 생성
    @PostMapping("/group")
    public ResponseDto<String> addGroup(@RequestBody GroupRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 알람 보내기
//        alertService.alertEvent(requestDto);
        return groupService.addGroup(requestDto, userDetails.getUser());
    }

    // 친구 그룹 목록 조회
    @GetMapping("/group")
    public ResponseDto<List<GroupResponseDto.groupList>> getAllGroups(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return groupService.getAllGroups(userDetails.getUser());
    }

    // 친구 그룹 목록 하나 조회
    @GetMapping("/group/{groupId}")
    public ResponseDto<GroupResponseDto.groupDetail> getOneGroup(@PathVariable Long groupId) {
        return groupService.getOneGroup(groupId);
    }

    // 그룹에 친구 추가
    @PostMapping("/group/{groupId}")
    public ResponseDto<String> addFreindToGroup(@RequestBody GroupAddRequestDto requestDto, @PathVariable Long groupId) {
        return groupService.addFriendToGroup(groupId, requestDto);
    }

    // 그룹에서 나가기
    @PostMapping("/group/exit/{groupId}")
    public ResponseDto<String> exitGroup(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long groupId) {
        return groupService.exitGroup(userDetails.getUser(), groupId);
    }
}
