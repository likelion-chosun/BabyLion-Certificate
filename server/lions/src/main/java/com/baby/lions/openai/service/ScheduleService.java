package com.baby.lions.openai.service;

import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.openai.repository.ScheduleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Map<String, String>> scheduleMaps = objectMapper.readValue(responseContent, List.class);
        List<Schedule> schedules = new ArrayList<>();

        for (Map<String, String> scheduleMap : scheduleMaps) {
            String title = scheduleMap.get("title");
            String description = scheduleMap.get("description");
            schedules.add(new Schedule(title, description));
        }

        return schedules;
    }

    public List<Schedule> getSchedules(){
        return scheduleRepository.findAll();
    }

}
