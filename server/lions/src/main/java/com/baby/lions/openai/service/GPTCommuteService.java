package com.baby.lions.openai.service;

import com.baby.lions.openai.dto.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Getter
public class GPTCommuteService {
	private final String model;
	private final String apiURL;
	private final RestTemplate template;

	public GPTCommuteService(@Value("${openai.model}") String model, @Value("${openai.url}") String apiURL, RestTemplate template) {
		this.model = model;
		this.apiURL = apiURL;
		this.template = template;
	}

	public ChatGPTResponse getChatGPTResponse(ChatGPTRequest request) {
		return template.postForObject(apiURL, request, ChatGPTResponse.class);
	}

}
