package com.moa.moabackend.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScheduleResponseDto {
    private Long id;

    private String meetingDate;
//    private LocalDateTime meetingDate;

    private String title;

    private String meetingTime;
//    private LocalDateTime meetingTime;

    private String location;

    private String content;
}
