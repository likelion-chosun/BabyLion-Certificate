package com.baby.lions.schedulemanage.repository;

import com.baby.lions.schedulemanage.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {
    List<Calendar> findByDate(LocalDate date);

    List<Calendar> findByTitleAndDate(String title, LocalDate date);
}
