package com.moa.moabackend.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    //    private LocalDateTime meetingDate;
    private String meetingDate;

    private String title;

    //    private LocalDateTime meetingTime;
    private String meetingTime;

    private String location;

    private String content;
}
