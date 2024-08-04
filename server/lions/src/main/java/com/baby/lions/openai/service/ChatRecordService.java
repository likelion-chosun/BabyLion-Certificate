package com.baby.lions.openai.service;

import com.baby.lions.login.entity.User;
import com.baby.lions.openai.entity.ChatRecord;
import com.baby.lions.openai.repository.ChatRecordRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRecordService {
    private final ChatRecordRepository chatRecordRepository;

    public void saveChatRecord(Long userId, String userMessage, String botMessage) {
        ChatRecord chatRecord = ChatRecord.builder()
                .userMessage(userMessage)
                .botMessage(botMessage)
                .user(User.builder().id(userId).build()) // 사용자 설정
                .build();
        chatRecordRepository.save(chatRecord);
    }
}