package com.moa.moabackend.entity.schedule;

import com.moa.moabackend.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String startDate;

    private String endDate;

    private String title;

    private String startTime;

    private String endTime;

    private String location;

    private String locationRoadName;

    private String content;
}
