package com.baby.lions.openai.controller;


import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.openai.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/recommend")
    public ResponseEntity<List<Schedule>> getRecommandSchedules(){
        List<Schedule> schedules = scheduleService.getSchedules();
        return ResponseEntity.ok(schedules);
    }
}
