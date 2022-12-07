package com.moa.moabackend.entity.friend;

import com.moa.moabackend.entity.Timestamped;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Friend extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userId;

    private String userName;

    private String friendUsername;


    public void updateUserName(String userName) {
        this.setUserName(userName);
    }

    public void updateFriendUserName(String userName) {
        this.setFriendUsername(userName);
    }
}
