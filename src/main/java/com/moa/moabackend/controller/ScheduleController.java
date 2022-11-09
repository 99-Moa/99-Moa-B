package com.moa.moabackend.controller;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.schedule.ScheduleRequestDto;
import com.moa.moabackend.entity.schedule.ScheduleResponseDto;
import com.moa.moabackend.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    // calendar
    @GetMapping("/calendar")
    public ResponseDto<List<ScheduleResponseDto>> getCalendar () {
        return scheduleService.getCalendar();
    }

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseDto<ScheduleResponseDto> addSchedule (@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.addSchedule(requestDto);
    }

    // 일정 목록 조회
    @GetMapping("/schedules")
    public ResponseDto<List<ScheduleResponseDto>> getAllSchedules () {
        return scheduleService.getAllSchedules();
    }

    // 일정 상세 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseDto<ScheduleResponseDto> getOneSchedule (@PathVariable Long scheduleId) {
        return scheduleService.getOneSchedule(scheduleId);
    }

    // 일정 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseDto<String> updateSchedule (@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto);
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseDto<String> deleteSchedule (@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
}
