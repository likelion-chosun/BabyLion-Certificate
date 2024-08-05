package com.baby.lions.schedulemanage.dto;

import lombok.Data;

@Data
public class CalendarResponse {

    private Long id;
    private String title;
    private String date;
    private String startTime;
    private String endTime;

    public CalendarResponse(String title, String date, String startTime, String endTime) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CalendarResponse(Long id, String title, String date, String startTime, String endTime) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CalendarResponse(String title, String startTime, String endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
