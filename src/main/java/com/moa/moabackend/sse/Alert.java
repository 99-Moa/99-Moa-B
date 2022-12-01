package com.moa.moabackend.sse;

import com.moa.moabackend.entity.schedule.Schedule;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String message;

    @Column
    private String receiver;

    @Column
    private boolean check;
}
