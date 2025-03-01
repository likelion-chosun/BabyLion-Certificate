package com.baby.lions.openai.dto;

import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTResponse {

    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
    }
}

