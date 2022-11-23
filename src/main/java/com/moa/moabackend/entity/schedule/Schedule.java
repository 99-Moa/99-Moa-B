package com.moa.moabackend.entity.schedule;

import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String title;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Column
    private String location;

    @Column
    private String locationRoadName;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany
//    private List<String> userNameList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    public void update(ScheduleRequestDto requestDto) {
        this.startDate = LocalDate.parse(requestDto.getStartDate());
        this.endDate = LocalDate.parse(requestDto.getEndDate());
        this.title = requestDto.getTitle();
        this.startTime = LocalTime.parse(requestDto.getStartTime());
        this.endTime = LocalTime.parse(requestDto.getEndTime());
        this.location = requestDto.getLocation();
        this.locationRoadName = requestDto.getLocationRoadName();
        this.content = requestDto.getContent();
    }
}
