package com.moa.moabackend.entity.group;

import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column
    private String groupName;

    @Convert(converter = StringListConverter.class)
    @Column
    private List<String> users;

    // 그룹에 참가한 사람 수
    @Column
    private int userNum;

    @Column
    private boolean isPlan;

    public void updateGroup(String userName) {
        users.add(userName);
    }
}
