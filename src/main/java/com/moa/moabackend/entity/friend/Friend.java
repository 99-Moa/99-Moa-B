package com.moa.moabackend.entity.friend;

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
public class Friend {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long userId;

    private String userName;

    private String friendUsername;

    public Friend(Long userId, String userName, String friendUsername) {
        this.userId = userId;
        this.userName = userName;
        this.friendUsername = friendUsername;
    }
}
