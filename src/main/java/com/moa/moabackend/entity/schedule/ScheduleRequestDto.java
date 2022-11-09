package com.moa.moabackend.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String meetingDate;

    private String title;

    private String meetingTime;

    private String location;

    private String content;
}
