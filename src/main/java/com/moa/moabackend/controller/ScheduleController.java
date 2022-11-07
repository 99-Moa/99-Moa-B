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
}
