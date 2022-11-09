package com.moa.moabackend.repository;

import com.moa.moabackend.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByMeetingDate();
}
