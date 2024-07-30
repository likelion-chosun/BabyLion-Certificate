package com.baby.lions.schedulemanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CalendarDayResponse {

    private String day;
    private String weekday;
    private List<CalendarResponse> dayevent;

}
