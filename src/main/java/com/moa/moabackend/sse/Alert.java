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

    // 알람 보내는 사람 닉네임 수정 시
    public void updateAlert(String sender, String imgUrl) {
        this.sender = sender;
        this.message = sender + "님이 " + groupName + " 그룹에 초대했습니다.";
        this.imgUrl = imgUrl;
    }
}
