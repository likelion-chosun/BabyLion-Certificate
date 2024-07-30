package com.baby.lions.schedulemanage.controller;

import com.baby.lions.schedulemanage.dto.CalendarDayResponse;
import com.baby.lions.schedulemanage.dto.CalendarRequest;
import com.baby.lions.schedulemanage.dto.CalendarResponse;
import com.baby.lions.schedulemanage.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/adddirect")
    public ResponseEntity<CalendarResponse> createEvent(@RequestBody CalendarRequest request) {
        CalendarResponse response = calendarService.createEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<List<CalendarResponse>> createEvents(@RequestBody CalendarRequest request) {
        try {
            List<CalendarResponse> responses = calendarService.createEvents(request);
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{date}")
    public ResponseEntity<CalendarDayResponse> getEventsByDate(@PathVariable String date) {
        CalendarDayResponse response = calendarService.getEventsByDate(date);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CalendarResponse> updateEvent(@PathVariable("id") Long eventId, @RequestBody CalendarRequest updateRequest) {

        CalendarResponse updatedEvent = calendarService.updateEvent(eventId, updateRequest);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId) {

        calendarService.deleteEvent(eventId);
        return new ResponseEntity<>("일정이 삭제되었습니다.",HttpStatus.CREATED);
    }
}
