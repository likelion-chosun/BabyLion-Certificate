package com.baby.lions.schedulemanage.service;


import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.openai.repository.ScheduleRepository;
import com.baby.lions.schedulemanage.dto.CalendarDayResponse;
import com.baby.lions.schedulemanage.dto.CalendarRequest;
import com.baby.lions.schedulemanage.dto.CalendarResponse;
import com.baby.lions.schedulemanage.dto.EventRequest;
import com.baby.lions.schedulemanage.entity.Calendar;
import com.baby.lions.schedulemanage.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final ScheduleRepository scheduleRepository;
    private final CalendarRepository calendarRepository;

    private LocalDate convertToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private LocalTime convertToLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
    }

    // 일정 직접 받아서 추가하기
    @Transactional
    public CalendarResponse createEvent(CalendarRequest request) {
        LocalDate date = convertToLocalDate(request.getDate());
        LocalTime startTime = convertToLocalTime(request.getStartTime());
        LocalTime endTime = convertToLocalTime(request.getEndTime());

        // CalendarEvent 엔티티 생성
        Calendar event = new Calendar();
        event.setTitle(request.getTitle());
        event.setDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);

        // 엔티티를 데이터베이스에 저장
        calendarRepository.save(event);

        // 저장한 엔티티를 DTO로 변환하여 반환
        return new CalendarResponse(event.getTitle(), event.getDate().toString(),
                event.getStartTime().toString(), event.getEndTime().toString());
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Transactional
    public List<CalendarResponse> createEvents(CalendarRequest request) {
        List<CalendarResponse> responseList = new ArrayList<>();
        List<Schedule> schedules = getSchedules();

        LocalDate date = convertToLocalDate(request.getEvents().get(0).getDate());

        for (EventRequest eventRequest : request.getEvents()) {
            Long scheduleId = eventRequest.getScheduleId();
            LocalTime startTime = LocalTime.parse(eventRequest.getStartTime());
            LocalTime endTime = LocalTime.parse(eventRequest.getEndTime());

            String title = schedules.stream()
                    .filter(schedule -> schedule.getId().equals(scheduleId))
                    .map(Schedule::getTitle)
                    .findFirst()
                    .orElse("알 수 없는 일정");

            Calendar event = new Calendar();
            event.setTitle(title);
            event.setDate(date);
            event.setStartTime(startTime);
            event.setEndTime(endTime);

            calendarRepository.save(event);

            CalendarResponse response = new CalendarResponse(
                    event.getTitle(),
                    event.getDate().toString(),
                    event.getStartTime().toString(),
                    event.getEndTime().toString()
            );
            responseList.add(response);
        }

        return responseList;
    }

    @Transactional
    public List<CalendarResponse> getEventByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);

        List<Calendar> events = calendarRepository.findByDate(localDate);
        List<CalendarResponse> responseList = new ArrayList<>();

        for (Calendar event : events) {
            CalendarResponse response = new CalendarResponse(
                    event.getTitle(),
                    event.getDate().toString(),
                    event.getStartTime().toString(),
                    event.getEndTime().toString()
            );
            responseList.add(response);
        }

        return responseList;
    }

    @Transactional
    public CalendarDayResponse getEventsByDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        List<Calendar> events = calendarRepository.findAll();
        List<Calendar> filteredEvents = events.stream()
                .filter(event -> event.getDate().isEqual(localDate))
                .toList();

        List<CalendarResponse> scheduleResponses = filteredEvents.stream()
                .map(event -> new CalendarResponse(
                        event.getTitle(),
                        event.getStartTime().toString(),
                        event.getEndTime().toString()))
                .collect(Collectors.toList());

        String weekday = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);

        return new CalendarDayResponse(date, weekday, scheduleResponses);
    }

    @Transactional
    public CalendarResponse updateEvent(Long eventId, CalendarRequest updateRequest) {

        Calendar event = calendarRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        if (updateRequest.getTitle() != null) {
            event.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getDate() != null) {
            LocalDate date = convertToLocalDate(updateRequest.getDate());
            event.setDate(date);
        }
        if (updateRequest.getStartTime() != null) {
            LocalTime startTime = convertToLocalTime(updateRequest.getStartTime());
            event.setStartTime(startTime);
        }
        if (updateRequest.getEndTime() != null) {
            LocalTime endTime = convertToLocalTime(updateRequest.getEndTime());
            event.setEndTime(endTime);
        }


        calendarRepository.save(event);

        return new CalendarResponse(event.getTitle(), event.getDate().toString(),event.getStartTime().toString(), event.getEndTime().toString());
    }


    @Transactional
    public void deleteEvent(Long eventId) {
        if(!calendarRepository.existsById(eventId)){
            throw new RuntimeException("일정을 찾을 수 없습니다.");
        }
        calendarRepository.deleteById(eventId);
    }


}
