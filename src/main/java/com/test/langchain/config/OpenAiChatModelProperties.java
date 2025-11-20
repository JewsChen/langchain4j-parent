package com.test.langchain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "open-ai.chat-model")
public class OpenAiChatModelProperties {

    private String apiKey;
    private String modelName;
    private String baseUrl;
    private boolean logRequests;
    private boolean logResponses;
}
