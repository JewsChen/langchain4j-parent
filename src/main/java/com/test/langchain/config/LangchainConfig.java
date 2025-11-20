package com.test.langchain.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangchainConfig {
    @Resource
    private OpenAiChatModelProperties properties;

    @Bean
    public OpenAiChatModel openAiChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(properties.getApiKey())
                .modelName(properties.getModelName())
                .baseUrl(properties.getBaseUrl())
                .logRequests(properties.isLogRequests())
                .logResponses(properties.isLogResponses())
                .build();
    }
}
