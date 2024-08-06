package com.baby.lions.openai.service;


import com.baby.lions.openai.dto.ChatGPTRequest;
import com.baby.lions.openai.dto.ChatGPTResponse;
import com.baby.lions.openai.entity.Schedule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcTemplate jdbcTemplate;

    //DB 지정 필요
//    public void resetRepositories() {
//        Long userId = SecurityUtils.getCurrentUserId();
//        if (userId != null) {
//            jdbcTemplate.update("DELETE FROM schedule WHERE user_id = ?", userId);
//            jdbcTemplate.update("DELETE FROM chat_record WHERE user_id = ?", userId);
//        } else {
//            throw new IllegalStateException("사용자 ID를 찾을 수 없습니다.");
//        }
//    }
        // 로그인 구현되면 주석 풀 것
//    public String createSchedules(String prompt) throws JsonProcessingException {
//        Long userId = SecurityUtils.getCurrentUserId();
//        if (userId == null) {
//            throw new IllegalStateException("사용자 ID를 찾을 수 없습니다.");
//        }
//
//        //resetRepositories();
//        String input;
//        try {
//            Map<String, String> promptMap = objectMapper.readValue(prompt, Map.class);
//            input = promptMap.get("prompt");
//            if (input == null) {
//                throw new IllegalArgumentException("Prompt JSON must contain a 'prompt' field");
//            }
//        } catch (JsonProcessingException e) {
//            log.error("JSON 파싱 오류: ", e);
//            throw e;
//        }
//
//        log.info("input: {}", input);
//
//        String basePrompt = "사용자의 입력을 바탕으로 4개의 일정을 생성하고, 각 일정의 이유를 엄청 간략하게 설명해주세요. 답은 앞에 ~해서는 빼고 딱 잘라서 ~하기 좋은 날이에요, ~하시는 건 어떤가요? 이런식으로 공손하게" +
//                "형식은 다음과 같습니다: [\n" +
//                "  {\"title\": \"일정 내용\", \"description\": \"이유\"},\n" +
//                "  {\"title\": \"다음 일정 내용\", \"description\": \"다음 일정의 이유\"}\n" +
//                "]\n" +
//                "예시입니다. [\n" +
//                "  {\"title\": \"운동 하기\", \"description\": \"오늘은 미세먼지가 적어요\"},\n" +
//                "  {\"title\": \"피크닉 가기\", \"description\": \"오늘은 날씨가 맑아요\"},\n" +
//                "  {\"title\": \"영화 보기\", \"description\": \"영화 감상은 어떠신가요\"},\n" +
//                "  {\"title\": \"카페 가기\", \"description\": \"음료 한잔 어떠신가요\"}\n" +
//                "]\n" +
//                "입력: " + input;
//
//        ChatGPTRequest request = new ChatGPTRequest("gpt-3.5-turbo", basePrompt, 1, 256, 1, 0, 0);
//        ChatGPTResponse response = gptCommuteService.getChatGPTResponse(request);
//
//        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
//            throw new IllegalArgumentException("유효하지 않은 응답입니다.");
//        }
//
//        String content = response.getChoices().get(0).getMessage().getContent();
//        chatRecordService.saveChatRecord(userId, input, content);
//
//        List<Schedule> schedules;
//        try {
//            schedules = scheduleService.parseSchedules(content);
//        } catch (JsonProcessingException e) {
//            log.error("스케줄 파싱 오류: ", e);
//            throw e;
//        }
//
//        scheduleService.saveSchedules(userId, schedules);
//
//        return content;
//    }
//
    public String createSchedules(String prompt) throws JsonProcessingException {

	    //resetRepositories();
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

        String basePrompt = "사용자의 입력을 바탕으로 4개의 일정을 생성하고, 각 일정의 이유를 엄청 간략하게 설명해주세요. 답은 앞에 ~해서는 빼고 딱 잘라서 ~하기 좋은 날이에요, ~하시는 건 어떤가요? 이런식으로 공손하게" +
                "형식은 다음과 같습니다: [\n" +
                "  {\"title\": \"일정 내용\", \"description\": \"이유\"},\n" +
                "  {\"title\": \"다음 일정 내용\", \"description\": \"다음 일정의 이유\"}\n" +
                "]\n" +
                "예시입니다. [\n" +
                "  {\"title\": \"운동 하기\", \"description\": \"오늘은 미세먼지가 적어요\"},\n" +
                "  {\"title\": \"피크닉 가기\", \"description\": \"오늘은 날씨가 맑아요\"},\n" +
                "  {\"title\": \"영화 보기\", \"description\": \"영화 감상은 어떠신가요\"},\n" +
                "  {\"title\": \"카페 가기\", \"description\": \"음료 한잔 어떠신가요\"}\n" +
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
