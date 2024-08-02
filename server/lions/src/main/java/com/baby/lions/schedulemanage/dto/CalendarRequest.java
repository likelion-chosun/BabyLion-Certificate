package com.baby.lions.schedulemanage.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarRequest {
    private List<EventRequest> events;

    private Long id;
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
