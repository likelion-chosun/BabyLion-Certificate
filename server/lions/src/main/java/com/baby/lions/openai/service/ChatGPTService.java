package com.baby.lions.openai.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.baby.lions.openai.dto.*;
import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.openai.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChatGPTService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.url.prompt}")
    private String apiURL;

    private final RestTemplate template;
    private final ChatRecordRepository chatRecordRepository;
    private final ScheduleRepository scheduleRepository;
    private final ObjectMapper objectMapper;

    public ChatGPTResponse getChatGPTResponse(ChatGPTRequest request) {
        ChatGPTResponse response = template.postForObject(apiURL, request, ChatGPTResponse.class);
        return response;
    }

    public void saveChatRecord(String prompt, ChatGPTResponse response) {
        ChatRecord chatRecord = ChatRecord.builder()
                .userMessage(prompt)
                .botMessage(response.getChoices().get(0).getMessage().getContent())
                .build();

        chatRecordRepository.save(chatRecord);
    }

    public String handleChatRequest(String prompt) throws JsonProcessingException {
        String input = objectMapper.readValue(prompt, Map.class).get("prompt").toString();
        log.info("input: " + input);

        String basePrompt = "사용자의 입력을 바탕으로 4개의 일정을 생성하고, 각 일정의 이유를 간략하게 설명해주세요. " +
                "형식은 다음과 같습니다: [{\"title\": \"일정 내용\", \"description\": \"이유\"}...] 예시입니다. " +
                "\"title\" : \"운동하기\", \"description\" : \"오늘은 미세먼지가 적어요\",; 입력: " + input;

        ChatGPTRequest request = new ChatGPTRequest(model, basePrompt, 1, 256, 1, 0, 0);
        ChatGPTResponse response = getChatGPTResponse(request);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 응답입니다.");
        }

        String content = response.getChoices().get(0).getMessage().getContent();
        saveChatRecord(input, content);
        saveSchedules(content);

        return content;
    }


    private void saveChatRecord(String prompt, String responseContent) {
        ChatRecord chatRecord = ChatRecord.builder()
                .userMessage(prompt)
                .botMessage(responseContent)
                .build();

        chatRecordRepository.save(chatRecord);
    }

    private void saveSchedules(String responseContent) throws JsonProcessingException {
        List<Map<String, String>> schedules = objectMapper.readValue(responseContent, List.class);

        for (Map<String, String> scheduleMap : schedules) {
            String title = scheduleMap.get("title");
            String description = scheduleMap.get("description");
            Schedule schedule = new Schedule(title, description);
            scheduleRepository.save(schedule);
        }
    }

    public List<ScheduleResponse> getScheduleRecommendations(String mood) {
        List<Schedule> schedules = generateSchedules(mood);
        return convertToScheduleResponses(schedules);
    }

    private List<Schedule> generateSchedules(String userInput) {
        String basePrompt = "사용자의 입력을 바탕으로 4개의 일정을 생성하고, 각 일정의 이유를 간략하게 설명해주세요. " +
                "형식은 다음과 같습니다: [{\"title\": \"일정 내용\", \"description\": \"이유\"}...] 예시입니다. " +
                "\"title\" : \"운동하기\", \"description\" : \"오늘은 미세먼지가 적어요\"; 입력: " + userInput;

        ChatGPTRequest request = new ChatGPTRequest(model, basePrompt, 1, 256, 1, 0, 0);
        ChatGPTResponse response = getChatGPTResponse(request);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return Collections.emptyList();
        }

        String content = response.getChoices().get(0).getMessage().getContent();
        try {
            return parseSchedules(content);
        } catch (Exception e) {
            log.error("스케쥴 파싱 오류: ", e);
            return Collections.emptyList();
        }
    }

    private List<Schedule> parseSchedules(String responseContent) throws JsonProcessingException {
        List<Map<String, String>> scheduleMaps = objectMapper.readValue(responseContent, List.class);
        List<Schedule> schedules = new ArrayList<>();

        for (Map<String, String> scheduleMap : scheduleMaps) {
            String title = scheduleMap.get("title");
            String description = scheduleMap.get("description");
            schedules.add(new Schedule(title, description));
        }

        return schedules;
    }

    private List<ScheduleResponse> convertToScheduleResponses(List<Schedule> schedules) {
        return schedules.stream()
                .map(schedule -> new ScheduleResponse(schedule.getTitle(), schedule.getDescription()))
                .collect(Collectors.toList());
    }
}