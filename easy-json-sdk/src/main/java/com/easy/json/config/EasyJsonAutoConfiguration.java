package com.easy.json.config;

import com.easy.json.EasyJson;
import com.easy.json.core.JsonService;
import com.easy.json.impl.GsonJsonService;
import com.easy.json.impl.JacksonJsonService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EasyJsonAutoConfiguration {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "easy.json.provider", havingValue = "jackson", matchIfMissing = true)
    public JsonService jacksonJsonService() {
        return new JacksonJsonService();
    }

    @Bean
    @ConditionalOnProperty(name = "easy.json.provider", havingValue = "gson")
    public JsonService gsonJsonService() {
        return new GsonJsonService();
    }

    @Configuration
    public static class EasyJsonInitializer {
        private final JsonService jsonService;

        public EasyJsonInitializer(JsonService jsonService) {
            this.jsonService = jsonService;
        }

        @PostConstruct
        public void init() {
            EasyJson.setService(jsonService);
        }
    }
}
