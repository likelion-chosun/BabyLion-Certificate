package com.baby.lions.openai.controller;


import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.schedulemanage.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final CalendarService scheduleService;
    // 로그인 구현되면 주석 풀 것
//    @GetMapping("/recommend")
//    public ResponseEntity<List<ScheduleResponse>> getRecommandSchedules() {
//        try {
//            Long userId = SecurityUtils.getCurrentUserId();
//            if (userId == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
//            }
//            List<ScheduleResponse> schedules = scheduleService.getSchedules(userId);
//            return ResponseEntity.ok(schedules);
//        } catch (NoSuchElementException e) {
//            log.error("추천 스케줄을 찾을 수 없습니다: ", e);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
//        } catch (Exception e) {
//            log.error("추천 스케줄 요청 처리 중 오류가 발생: ", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
//        }
//    }
    @GetMapping("/recommend")
    public ResponseEntity<List<Schedule>> getRecommandSchedules() {
        try {

            List<Schedule> schedules = scheduleService.getSchedules();
            return ResponseEntity.ok(schedules);
        } catch (NoSuchElementException e) {
            log.error("추천 스케줄을 찾을 수 없습니다: ", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            log.error("추천 스케줄 요청 처리 중 오류가 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
