package com.moa.moabackend.entity.group;

import com.moa.moabackend.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
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
        private LocalDate startDate;
        private String location;
        private LocalTime startTime;
        private List<String> imgUrls;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class groupDetail {
        // 그룹 상세 조회
        private Long groupId;
        private List<Long> userIdList;
        private String groupName;
        private int userNum;
    }
}
