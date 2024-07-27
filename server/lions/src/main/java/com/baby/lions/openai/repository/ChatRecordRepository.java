package com.baby.lions.openai.repository;

import com.baby.lions.openai.dto.ChatRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRecordRepository extends JpaRepository<ChatRecord, Long> {
}