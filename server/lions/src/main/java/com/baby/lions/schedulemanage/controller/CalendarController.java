package com.baby.lions.schedulemanage.controller;

import com.baby.lions.schedulemanage.dto.CalendarDayResponse;
import com.baby.lions.schedulemanage.dto.CalendarRequest;
import com.baby.lions.schedulemanage.dto.CalendarResponse;
import com.baby.lions.schedulemanage.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/adddirect")
    public ResponseEntity<CalendarResponse> createEvent(@RequestBody CalendarRequest request) {
        try {
            CalendarResponse response = calendarService.createEvent(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("일정 추가 중 오류가 발생: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<List<CalendarResponse>> createEvents(@RequestBody CalendarRequest request) {
        try {
            List<CalendarResponse> responses = calendarService.createEvents(request);
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("여러 일정 추가 중 오류가 발생: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{date}")
    public ResponseEntity<CalendarDayResponse> getEventsByDate(@PathVariable String date) {
        try {
            CalendarDayResponse response = calendarService.getEventsByDate(date);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("일정 조회 중 오류가 발생: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/all")
    public ResponseEntity<List<CalendarDayResponse>> getAllEvents() {
        try {
            List<CalendarDayResponse> response = calendarService.getAllEvents();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("일정 조회 중 오류가 발생: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/update")
    public ResponseEntity<CalendarResponse> updateEvent(@RequestBody CalendarRequest updateRequest) {
        try {
            CalendarResponse updatedEvent = calendarService.updateEvent(updateRequest);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("일정 업데이트 중 오류가 발생: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("일정 업데이트 중 오류가 발생: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEvent(@RequestBody CalendarRequest deleteRequest) {
        try {
            calendarService.deleteEvent(deleteRequest);
            return new ResponseEntity<>("일정이 삭제되었습니다.", HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("일정 삭제 중 오류가 발생: ", e);
            return new ResponseEntity<>("일정을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("일정 삭제 중 오류가 발생: ", e);
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
