package com.baby.lions.openai.service;

import com.baby.lions.login.entity.User;
import com.baby.lions.login.repository.UserRepository;
import com.baby.lions.openai.dto.ScheduleResponse;
import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.openai.repository.ScheduleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public void saveSchedules(Long userId, List<Schedule> schedules) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID: " + userId));

        for (Schedule schedule : schedules) {
            schedule.setUser(user); // 각 일정에 사용자 설정
        }
        scheduleRepository.saveAll(schedules);
    }

    public List<ScheduleResponse> getSchedules(Long userId) {
        try {
            List<Schedule> schedules = scheduleRepository.findByUserId(userId);
            return schedules.stream()
                    .map(schedule -> new ScheduleResponse(
                            schedule.getId(),
                            schedule.getTitle(),
                            schedule.getDescription()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("스케줄을 가져오는 중 오류가 발생: ", e);
            throw new RuntimeException("스케줄을 가져오는 중 오류가 발생했습니다.", e);
        }
    }

    public List<Schedule> parseSchedules(String responseContent) throws JsonProcessingException {
        try {
            List<Map<String, String>> scheduleMaps = objectMapper.readValue(responseContent, List.class);
            List<Schedule> schedules = new ArrayList<>();

            for (Map<String, String> scheduleMap : scheduleMaps) {
                String title = scheduleMap.get("title");
                String description = scheduleMap.get("description");
                schedules.add(new Schedule(title, description));
            }

            return schedules;
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류: ", e);
            throw e;
        }
    }


}
