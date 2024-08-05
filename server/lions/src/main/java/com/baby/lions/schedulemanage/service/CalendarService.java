package com.baby.lions.schedulemanage.service;


import com.baby.lions.login.util.SecurityUtils;
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
import java.util.*;
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
        Long userId = SecurityUtils.getCurrentUserId();
        LocalDate date = convertToLocalDate(request.getDate());
        LocalTime startTime = convertToLocalTime(request.getStartTime());
        LocalTime endTime = convertToLocalTime(request.getEndTime());

        // CalendarEvent 엔티티 생성
        Calendar event = new Calendar();
        event.setTitle(request.getTitle());
        event.setDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        //event.setUser(userId);

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

            LocalTime endTime = eventRequest.getEndTime().isEmpty() ? null : LocalTime.parse(eventRequest.getEndTime());

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
                    event.getId(),
                    event.getTitle(),
                    event.getDate().toString(),
                    event.getStartTime().toString(),
                    event.getEndTime().toString()
            );
            responseList.add(response);
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<CalendarDayResponse> getAllEvents() {
        Long userId = SecurityUtils.getCurrentUserId();
        List<Calendar> events = calendarRepository.findAll();

        // 날짜를 기준으로 정렬하여 그룹화
        Map<LocalDate, List<Calendar>> eventsByDate = events.stream()
                .sorted(Comparator.comparing(Calendar::getDate)) // 날짜 기준으로 정렬
                .collect(Collectors.groupingBy(Calendar::getDate, LinkedHashMap::new, Collectors.toList()));

        List<CalendarDayResponse> calendarDayResponses = new ArrayList<>();

        for (Map.Entry<LocalDate, List<Calendar>> entry : eventsByDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<Calendar> dayEvents = entry.getValue();

            List<CalendarResponse> scheduleResponses = dayEvents.stream()
                    .map(event -> new CalendarResponse(
                            event.getId(),
                            event.getTitle(),
                            event.getDate().toString(),
                            event.getStartTime().toString(),
                            event.getEndTime().toString()))
                    .collect(Collectors.toList());

            String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
            CalendarDayResponse dayResponse = new CalendarDayResponse(
                    date.toString(), weekday, scheduleResponses);

            calendarDayResponses.add(dayResponse);
        }

        return calendarDayResponses;
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
                        event.getDate().toString(),
                        event.getStartTime().toString(),
                        event.getEndTime().toString()))
                .collect(Collectors.toList());

        String weekday = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);

        return new CalendarDayResponse(date, weekday, scheduleResponses);
    }

    @Transactional
    public CalendarResponse updateEvent(CalendarRequest updateRequest) {
        Long eventId = updateRequest.getId();
        Optional<Calendar> optionalEvent = calendarRepository.findById(eventId);

        if (!optionalEvent.isPresent()) {
            throw new RuntimeException("일정을 찾을 수 없습니다.");
        }

        Calendar event = optionalEvent.get();
        event.setTitle(updateRequest.getTitle());
        event.setDate(LocalDate.parse(updateRequest.getDate()));
        event.setStartTime(LocalTime.parse(updateRequest.getStartTime()));
        event.setEndTime(LocalTime.parse(updateRequest.getEndTime()));

        Calendar updatedEvent = calendarRepository.save(event);

        return new CalendarResponse(updatedEvent.getTitle(), updatedEvent.getDate().toString(), updatedEvent.getStartTime().toString(), updatedEvent.getEndTime().toString());
    }

    @Transactional
    public void deleteEvent(CalendarRequest deleteRequest) {
        Long eventId = deleteRequest.getId();

        if (!calendarRepository.existsById(eventId)) {
            throw new RuntimeException("일정을 찾을 수 없습니다.");
        }
        calendarRepository.deleteById(eventId);
    }
}
