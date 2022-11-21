package com.moa.moabackend.entity.group;

import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column
    private String groupName;

    @Convert(converter = StringListConverter.class)
    @Column
    private List<String> users;
    // List<User> users

    // 그룹에 참가한 사람 수
    @Column
    private int userNum;
}
