package com.moa.moabackend.entity.group;

import com.moa.moabackend.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class GroupResponseDto {
    private List<String> userList;
    private String groupName;
    private int userNum;
}
