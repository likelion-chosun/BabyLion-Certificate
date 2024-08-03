package com.baby.lions.schedulemanage.dto;

import lombok.Data;

@Data
public class EventRequest {
    private Long scheduleId;
    private String date;
    private String startTime;
    private String endTime;

}