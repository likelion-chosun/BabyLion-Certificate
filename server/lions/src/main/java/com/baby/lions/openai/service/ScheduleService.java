package com.baby.lions.openai.service;

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

@Slf4j
@AllArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ObjectMapper objectMapper;

    public void saveSchedules(String responseContent) throws JsonProcessingException {
        List<Schedule> schedules = parseSchedules(responseContent);
        scheduleRepository.saveAll(schedules);
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

    public List<Schedule> getSchedules() {
        try {
            return scheduleRepository.findAll();
        } catch (Exception e) {
            log.error("스케줄을 가져오는 중 오류가 발생: ", e);
            throw new RuntimeException("스케줄을 가져오는 중 오류가 발생했습니다.", e);
        }
    }


}
