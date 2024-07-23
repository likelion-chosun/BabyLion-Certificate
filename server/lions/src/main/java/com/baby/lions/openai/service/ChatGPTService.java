package com.baby.lions.openai.service;


import com.baby.lions.openai.dto.ChatGPTRequest;
import com.baby.lions.openai.dto.ChatGPTResponse;
import com.baby.lions.openai.entity.Schedule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChatGPTService {
    private final GPTCommuteService gptCommuteService;
    private final ChatRecordService chatRecordService;
    private final ScheduleService scheduleService;
    private final ObjectMapper objectMapper;

    public String createSchedules(String prompt) throws JsonProcessingException {
        String input;
        try {
            Map<String, String> promptMap = objectMapper.readValue(prompt, Map.class);
            input = promptMap.get("prompt");
            if (input == null) {
                throw new IllegalArgumentException("Prompt JSON must contain a 'prompt' field");
            }
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류: ", e);
            throw e;
        }

        log.info("input: {}", input);

        String basePrompt = "사용자의 입력을 바탕으로 4개의 일정을 생성하고, 각 일정의 이유를 간략하게 설명해주세요. " +
                "형식은 다음과 같습니다: [\n" +
                "  {\"title\": \"일정 내용\", \"description\": \"이유\"},\n" +
                "  {\"title\": \"다음 일정 내용\", \"description\": \"다음 일정의 이유\"}\n" +
                "]\n" +
                "예시입니다. [\n" +
                "  {\"title\": \"영화 보기\", \"description\": \"기분 전환을 위해서\"},\n" +
                "  {\"title\": \"책 읽기\", \"description\": \"스트레스 해소를 위해서\"},\n" +
                "  {\"title\": \"친구와 대화하기\", \"description\": \"소통과 만족을 위해서\"},\n" +
                "  {\"title\": \"산책하기\", \"description\": \"신선한 공기를 마시고 마음의 안정을 찾기 위해서\"}\n" +
                "]\n" +
                "입력: " + input;

        ChatGPTRequest request = new ChatGPTRequest("gpt-3.5-turbo", basePrompt, 1, 256, 1, 0, 0);
        ChatGPTResponse response = gptCommuteService.getChatGPTResponse(request);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 응답입니다.");
        }

        String content = response.getChoices().get(0).getMessage().getContent();
        chatRecordService.saveChatRecord(input, content);

        List<Schedule> schedules;
        try {
            schedules = scheduleService.parseSchedules(content);
        } catch (JsonProcessingException e) {
            log.error("스케줄 파싱 오류: ", e);
            throw e;
        }

        scheduleService.saveSchedules(objectMapper.writeValueAsString(schedules));

        return content;
    }
}
