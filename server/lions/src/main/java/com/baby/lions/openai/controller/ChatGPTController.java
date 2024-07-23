package com.baby.lions.openai.controller;

import com.baby.lions.openai.dto.ScheduleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.baby.lions.openai.service.ChatGPTService;
import com.baby.lions.openai.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;
    private final ScheduleService scheduleService;

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody @Valid String prompt) {
        try {
            String responseContent = chatGPTService.createSchedules(prompt);
            return ResponseEntity.ok(responseContent);
        } catch (JsonProcessingException e) {
            log.error("JSON 처리 오류: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
        } catch (Exception e) {
            log.error("채팅 요청 처리 중 오류가 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing chat request");
        }
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResponse>> getSchedule(@RequestParam("mood") String mood) {
        try {
            List<ScheduleResponse> schedules = scheduleService.getScheduleRecommendations(mood);
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            log.error("일정 추천 중 오류가 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}