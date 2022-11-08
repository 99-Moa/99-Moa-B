package com.moa.moabackend.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String meetingDate;
//    private LocalDateTime meetingDate;

    @Column
    private String title;

    @Column
    private String meetingTime;
//    private LocalDateTime meetingTime;

    @Column
    private String location;

    @Column
    private String content;

    public void update(ScheduleRequestDto requestDto) {
        this.meetingDate = requestDto.getMeetingDate();
        this.title = requestDto.getTitle();
        this.meetingTime = requestDto.getMeetingTime();
        this.location = requestDto.getLocation();
        this.content = requestDto.getContent();
    }
}
