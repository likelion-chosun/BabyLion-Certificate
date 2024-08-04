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
import java.util.Map;


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
			log.info(responseContent);
			return ResponseEntity.ok(responseContent);
		} catch (JsonProcessingException e) {
			log.error("JSON 처리 오류: ", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 JSON 요청입니다.");
		} catch (Exception e) {
			log.error("채팅 요청 처리 중 오류가 발생: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("채팅 처리중 오류가 발생했습니다.");
		}
	}
}