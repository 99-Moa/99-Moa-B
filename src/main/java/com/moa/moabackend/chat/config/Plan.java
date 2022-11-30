package com.moa.moabackend.chat.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    private Long chatRoomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String locationRoadName;
    private String content;
}
