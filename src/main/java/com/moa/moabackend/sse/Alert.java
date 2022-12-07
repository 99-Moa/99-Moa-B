package com.moa.moabackend.sse;

import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.user.mypage.MypageRequestDto;
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
    private String sender;

    @Column
    private String imgUrl;

    @Column
    private String groupName;

    @Column
    private boolean check;

    // 그룹 알람일 시 0, 친구 알람일 시 1
    private int alertType;

    // 알람 보내는 사람 닉네임 수정 시
    public void updateAlert(String sender, String imgUrl) {
        this.sender = sender;
        if (alertType == 0) {
            this.message = sender + "님이 " + groupName + " 그룹에 초대했습니다.";
        } else {
            this.message = sender + "님이 친구추가 하셨습니다.";
        }
        this.imgUrl = imgUrl;
    }
}
