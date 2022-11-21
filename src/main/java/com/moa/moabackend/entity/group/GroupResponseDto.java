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
    @AllArgsConstructor
    @Getter
    @Builder
    public static class groupList {
        // 그룹 목록 조회
        private Long groupId;
        private String groupName;
        private int userNum;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class groupDetail {
        // 그룹 상세 조회
        private Long groupId;
        private List<String> users;
        private String groupName;
        private int userNum;
    }
}
