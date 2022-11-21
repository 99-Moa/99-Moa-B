package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.schedule.ScheduleRequestDto;
import com.moa.moabackend.entity.schedule.ScheduleResponseDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.GroupRepository;
import com.moa.moabackend.repository.ScheduleRepository;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    // calendar
    public ResponseDto<List<ScheduleResponseDto.Calendar>> getCalendar(User user) {
        List<ScheduleResponseDto.Calendar> scheduleResponseLIst = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAllByUser(user);
        for (Schedule schedule : schedules) {
            scheduleResponseLIst.add(
                    ScheduleResponseDto.Calendar.builder()
                            .meetingDate(schedule.getMeetingDate())
                            .build()
            );
        }
        return ResponseDto.success(scheduleResponseLIst);
    }

    // 일정 생성
    public ResponseDto<String> addSchedule(Long groupId, ScheduleRequestDto requestDto, User user) {
        Group group = groupRepository.findById(groupId).get();
        Schedule schedule = Schedule.builder()
                .meetingDate(LocalDate.parse(requestDto.getMeetingDate()))
                .title(requestDto.getTitle())
                .meetingTime(LocalTime.parse(requestDto.getMeetingTime()))
                .location(requestDto.getLocation())
                .content(requestDto.getContent())
                .user(user)
//                .userNameList(requestDto.getUserNameList())
                .group(group)
                .build();
        scheduleRepository.save(schedule);
        return ResponseDto.success("일정 등록 성공");
    }

    // 일정 목록 조회
    public ResponseDto<List<ScheduleResponseDto.ScheduleList>> getAllSchedules(User user) {
        List<ScheduleResponseDto.ScheduleList> scheduleResponseLIst = new ArrayList<>();
//        List<Schedule> schedules = scheduleRepository.findAllByOrderByMeetingDate();
        List<Schedule> schedules = scheduleRepository.findAllByUser(user);
        for (Schedule schedule : schedules) {
            scheduleResponseLIst.add(
                    ScheduleResponseDto.ScheduleList.builder()
                            .id(schedule.getId())
                            .meetingDate(schedule.getMeetingDate())
                            .title(schedule.getTitle())
                            .build()
            );
        }
        return ResponseDto.success(scheduleResponseLIst);
    }

    // 일정 상세 조회
    public ResponseDto<ScheduleResponseDto.ScheduleDetail> getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        List<User> userResponseList = new ArrayList<>();
        for (int i = 0; i <= schedule.getGroup().getUserNum()-1; i ++) {
            User user = userRepository.findByUserName(schedule.getGroup().getUserList().get(i)).get();
            userResponseList.add(user);
        }
        return ResponseDto.success(
                ScheduleResponseDto.ScheduleDetail.builder()
                        .meetingDate(schedule.getMeetingDate())
                        .title(schedule.getTitle())
                        .meetingTime(schedule.getMeetingTime())
                        .location(schedule.getLocation())
                        .content(schedule.getContent())
//                        .userNameList(schedule.getUserNameList())
//                        .userList(schedule.getGroup().getUserList())
                        .userList(userResponseList)
                        .build()
        );
    }

    // 일정 수정
    public ResponseDto<String> updateSchedule(Long scheduleId, ScheduleRequestDto requestDto, User user) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        if (user.getUserId() != schedule.getUser().getUserId()) {
            return ResponseDto.fail(404, "사용자가 일치하지 않습니다.", "Not Found");
        }
        schedule.update(requestDto);
        scheduleRepository.save(schedule);
        return ResponseDto.success("일정이 수정되었습니다.");
    }

    // 일정 삭제
    public ResponseDto<String> deleteSchedule(Long scheduleId, User user) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        if (user.getUserId() != schedule.getUser().getUserId()) {
            return ResponseDto.fail(404, "사용자가 일치하지 않습니다.", "Not Found");
        }
        scheduleRepository.delete(schedule);
        return ResponseDto.success("일정이 삭제되었습니다.");
    }
}
