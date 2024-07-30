package com.baby.lions.schedulemanage.dto;


import lombok.Data;

import java.util.List;

@Data
public class CalendarRequest {
    private List<EventRequest> events;

    private String title;
    private String date;
    private String startTime;
    private String endTime;

    public CalendarRequest(String title, String date, String startTime, String endTime) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
