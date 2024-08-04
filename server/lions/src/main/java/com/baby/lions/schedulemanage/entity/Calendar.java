package com.baby.lions.schedulemanage.entity;

import com.baby.lions.login.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Calendar(String title, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
