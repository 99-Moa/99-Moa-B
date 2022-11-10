package com.moa.moabackend.entity.schedule;

import com.moa.moabackend.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScheduleResponseDto {
    private Long id;

    private LocalDate meetingDate;

    private String title;

    private LocalTime meetingTime;

    private String location;

    private String content;

//    private List<String> userNameList;
}
