package com.baby.lions.openai.service;


import com.baby.lions.openai.dto.ChatRecord;
import com.baby.lions.openai.repository.ChatRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatRecordService {
    private final ChatRecordRepository chatRecordRepository;

    public void saveChatRecord(String prompt, String responseContent) {
        ChatRecord chatRecord = ChatRecord.builder()
                .userMessage(prompt)
                .botMessage(responseContent)
                .build();
        chatRecordRepository.save(chatRecord);
    }


}