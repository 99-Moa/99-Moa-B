package com.moa.moabackend.entity.friend;

import com.moa.moabackend.entity.user.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Friend extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userId;

    private String userName;

    private String friendUsername;

    public Friend(String userId, String userName, String friendUsername) {
        this.userId = userId;
        this.userName = userName;
        this.friendUsername = friendUsername;
    }
}
