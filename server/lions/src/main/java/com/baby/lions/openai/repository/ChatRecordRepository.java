package com.baby.lions.openai.repository;

import com.baby.lions.openai.dto.ChatRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatRecordRepository extends JpaRepository<ChatRecord, Long> {



//    @Modifying
//    @Transactional
//    @Query("delete from ChatRecord r")
//    void deleteAllWithQuery();
}