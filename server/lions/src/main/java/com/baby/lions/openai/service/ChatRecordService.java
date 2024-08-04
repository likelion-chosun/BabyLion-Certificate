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

    // 로그인 구현 되면 주석 풀 것
//    public void saveChatRecord(Long userId, String userMessage, String botMessage) {
//        ChatRecord chatRecord = ChatRecord.builder()
//                .userMessage(userMessage)
//                .botMessage(botMessage)
//                .user(User.builder().id(userId).build()) // 사용자 설정
//                .build();
//        chatRecordRepository.save(chatRecord);
//    }

    public void saveChatRecord(String userMessage, String botMessage) {
        ChatRecord chatRecord = ChatRecord.builder()
                .userMessage(userMessage)
                .botMessage(botMessage)
                .build();
        chatRecordRepository.save(chatRecord);
    }
}