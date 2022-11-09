package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.schedule.Schedule;
import com.moa.moabackend.entity.schedule.ScheduleRequestDto;
import com.moa.moabackend.entity.schedule.ScheduleResponseDto;
import com.moa.moabackend.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // calendar
    public ResponseDto<List<ScheduleResponseDto>> getCalendar() {
        List<ScheduleResponseDto> scheduleResponseLIst = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule schedule : schedules) {
            scheduleResponseLIst.add(
                    ScheduleResponseDto.builder()
                            .meetingDate(schedule.getMeetingDate())
                            .build()
            );
        }
        return ResponseDto.success(scheduleResponseLIst);
    }

    // 일정 생성
    public ResponseDto<ScheduleResponseDto> addSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.builder()
                .meetingDate(requestDto.getMeetingDate())
//                .meetingDate(LocalDateTime.parse(requestDto.getMeetingDate()))
                .title(requestDto.getTitle())
                .meetingTime(requestDto.getMeetingTime())
//                .meetingTime(LocalDateTime.parse(requestDto.getMeetingTime()))
                .location(requestDto.getLocation())
                .content(requestDto.getContent())
                .build();
        scheduleRepository.save(schedule);
        return ResponseDto.success(
                ScheduleResponseDto.builder()
                .meetingDate(schedule.getMeetingDate())
                .title(schedule.getTitle())
                .meetingTime(schedule.getMeetingTime())
                .location(schedule.getLocation())
                .content(schedule.getContent())
                .build());
    }

    // 일정 목록 조회
    public ResponseDto<List<ScheduleResponseDto>> getAllSchedules() {
        List<ScheduleResponseDto> scheduleResponseLIst = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule schedule : schedules) {
            scheduleResponseLIst.add(
                    ScheduleResponseDto.builder()
                            .id(schedule.getId())
                            .meetingDate(schedule.getMeetingDate())
                            .title(schedule.getTitle())
                            .build()
            );
        }
        return ResponseDto.success(scheduleResponseLIst);
    }

    // 일정 상세 조회
    public ResponseDto<ScheduleResponseDto> getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        return ResponseDto.success(
                ScheduleResponseDto.builder()
                        .meetingDate(schedule.getMeetingDate())
                        .title(schedule.getTitle())
                        .meetingTime(schedule.getMeetingTime())
                        .location(schedule.getLocation())
                        .content(schedule.getContent())
                        .build()
        );
    }

    // 일정 수정
    public ResponseDto<String> updateSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        schedule.update(requestDto);
        return ResponseDto.success("일정이 수정되었습니다.");
    }

    // 일정 삭제
    public ResponseDto<String> deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        scheduleRepository.delete(schedule);
        return ResponseDto.success("일정이 삭제되었습니다.");
    }
}
