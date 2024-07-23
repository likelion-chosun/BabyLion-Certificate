package com.group.openai.repository;

import com.group.openai.dto.ChatRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRecordRepository extends JpaRepository<ChatRecord, Long> {
}

