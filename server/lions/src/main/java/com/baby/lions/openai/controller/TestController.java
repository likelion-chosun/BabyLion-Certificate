package com.group.openai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.openai.dto.ScheduleResponse;
import com.group.openai.service.ChatGPTService;
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
public class TestController {

    private final ChatGPTService chatGPTService;
    private final ObjectMapper objectMapper;


    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody @Valid String prompt) {
        try {
            String responseContent = chatGPTService.handleChatRequest(prompt);
            return ResponseEntity.ok(responseContent);
        } catch (Exception e) {
            log.error("채팅 요청 처리 중 오류가 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing chat request");
        }
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResponse>> getSchedule(@RequestParam("mood") String mood) {
        try {
            List<ScheduleResponse> schedules = chatGPTService.getScheduleRecommendations(mood);
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            log.error("일정 추천 중 오류가 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
