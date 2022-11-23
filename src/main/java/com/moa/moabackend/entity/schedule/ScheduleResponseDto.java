package com.moa.moabackend.entity.schedule;

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
public class ScheduleResponseDto {

    @Getter
    @Builder
    public static class Calendar {
        // 캘린더 일자 조회
        private LocalDate startDate;
    }

    @Getter
    @Builder
    public static class ScheduleList {
        // 일정 목록 조회
        private Long id;

        private LocalDate startDate;

        private String title;

        private LocalTime startTime;

        private LocalDate endDate;

        private LocalTime endTime;
    }

    @Getter
    @Builder
    public static class ScheduleDetail {
        // 일정 상세 조회
        private LocalDate startDate;

        private LocalDate endDate;

        private String title;

        private LocalTime startTime;

        private LocalTime endTime;

        private String location;

        private String content;

        private List<User> users;

//    private List<String> userNameList;
    }
}
