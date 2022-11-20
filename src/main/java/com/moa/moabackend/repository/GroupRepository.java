package com.moa.moabackend.repository;

import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
//    List<Group> findAllByUserName(String userName);
}
