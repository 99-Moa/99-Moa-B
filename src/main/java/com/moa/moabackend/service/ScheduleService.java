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
                            .startDate(schedule.getStartDate())
                            .build()
            );
        }
        return ResponseDto.success(scheduleResponseLIst);
    }

    // 일정 생성 (개인)
    public ResponseDto<String> addSchedulePersonal(ScheduleRequestDto requestDto, User user) {
        Schedule schedule = Schedule.builder()
                .startDate(LocalDate.parse(requestDto.getStartDate()))
                .endDate(LocalDate.parse(requestDto.getEndDate()))
                .title(requestDto.getTitle())
                .startTime(LocalTime.parse(requestDto.getStartTime()))
                .endTime(LocalTime.parse(requestDto.getEndTime()))
                .location(requestDto.getLocation())
                .locationRoadName(requestDto.getLocationRoadName())
                .content(requestDto.getContent())
                .user(user)
                .group(null)
                .build();
        scheduleRepository.save(schedule);
        return ResponseDto.success("일정 등록 성공");
    }

    // 일정 생성 (그룹)
    public ResponseDto<String> addScheduleGroup(Long groupId, ScheduleRequestDto requestDto, User user) {
        Group group = groupRepository.findById(groupId).get();

        Schedule schedule = Schedule.builder()
                .startDate(LocalDate.parse(requestDto.getStartDate()))
                .endDate(LocalDate.parse(requestDto.getEndDate()))
                .title(requestDto.getTitle())
                .startTime(LocalTime.parse(requestDto.getStartTime()))
                .endTime(LocalTime.parse(requestDto.getEndTime()))
                .location(requestDto.getLocation())
                .locationRoadName(requestDto.getLocationRoadName())
                .content(requestDto.getContent())
                .user(user)
                .group(group)
                .build();
        scheduleRepository.save(schedule);
        return ResponseDto.success("일정 등록 성공");
    }

    // 일정 목록 조회
    public ResponseDto<List<ScheduleResponseDto.ScheduleList>> getAllSchedules(User user) {
        List<ScheduleResponseDto.ScheduleList> scheduleResponseLIst = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAllByUser(user);
        for (Schedule schedule : schedules) {
            scheduleResponseLIst.add(
                    ScheduleResponseDto.ScheduleList.builder()
                            .id(schedule.getId())
                            .startDate(schedule.getStartDate())
                            .title(schedule.getTitle())
                            .startTime(schedule.getStartTime())
                            .endDate(schedule.getEndDate())
                            .endTime(schedule.getEndTime())
                            .build()
            );
        }
        return ResponseDto.success(scheduleResponseLIst);
    }

    // 일정 상세 조회
    public ResponseDto<ScheduleResponseDto.ScheduleDetail> getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        List<User> userResponseList = new ArrayList<>();

        // group이 없으면 개인, 있으면 그룹 처리
        if (schedule.getGroup() == null) {
            userResponseList.add(schedule.getUser());
        } else {
            for (int i = 0; i <= schedule.getGroup().getUserNum() - 1; i++) {
                User user = userRepository.findByUserName(schedule.getGroup().getUsers().get(i)).get();
                userResponseList.add(user);
            }
        }

        return ResponseDto.success(
                ScheduleResponseDto.ScheduleDetail.builder()
                        .startDate(schedule.getStartDate())
                        .endDate(schedule.getEndDate())
                        .title(schedule.getTitle())
                        .startTime(schedule.getStartTime())
                        .endTime(schedule.getEndTime())
                        .location(schedule.getLocation())
                        .locationRoadName(schedule.getLocationRoadName())
                        .content(schedule.getContent())
                        .users(userResponseList)
                        .build()
        );
    }

    // 일정 수정
    public ResponseDto<String> updateSchedule(Long scheduleId, ScheduleRequestDto requestDto, User user) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        if (!user.getUserId().equals(schedule.getUser().getUserId())) {
            return ResponseDto.fail(404, "사용자가 일치하지 않습니다.", "Not Found");
        }
        schedule.update(requestDto);
        scheduleRepository.save(schedule);
        return ResponseDto.success("일정이 수정되었습니다.");
    }

    // 일정 삭제
    public ResponseDto<String> deleteSchedule(Long scheduleId, User user) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        if (!user.getUserId().equals(schedule.getUser().getUserId())) {
            return ResponseDto.fail(404, "사용자가 일치하지 않습니다.", "Not Found");
        }
        scheduleRepository.delete(schedule);
        return ResponseDto.success("일정이 삭제되었습니다.");
    }
}
