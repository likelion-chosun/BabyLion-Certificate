package com.baby.lions.openai.repository;


import com.baby.lions.openai.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

