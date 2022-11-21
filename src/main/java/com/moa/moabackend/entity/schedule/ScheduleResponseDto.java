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
        private LocalDate meetingDate;
    }

    @Getter
    @Builder
    public static class ScheduleList {
        // 일정 목록 조회
        private Long id;

        private LocalDate meetingDate;

        private String title;
    }

    @Getter
    @Builder
    public static class ScheduleDetail {
        // 일정 상세 조회
        private LocalDate meetingDate;

        private String title;

        private LocalTime meetingTime;

        private String location;

        private String content;

        private List<User> userList;

//    private List<String> userNameList;
    }
}
