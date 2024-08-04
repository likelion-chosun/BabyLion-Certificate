package com.baby.lions.openai.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@Slf4j
public class ChatGPTConfig {

    @Value("${openai.api.key}")
    private String openAiKey;

    @Bean
    public RestTemplate template() {
        RestTemplate restTemplate = new RestTemplate();

	    log.info("info: " + openAiKey);

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().setBearerAuth(openAiKey);
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}



