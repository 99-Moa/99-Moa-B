package com.moa.moabackend.entity.schedule;

import com.moa.moabackend.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String meetingDate;

    private String title;

    private String meetingTime;

    private String location;

    private String content;

//    private List<String> userNameList;

//    private List<String> userNameList;
}
