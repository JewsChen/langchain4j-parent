package com.easy.json.impl;

import com.easy.json.core.JsonService;
import com.easy.json.exception.JsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class JacksonJsonService implements JsonService {

    private final ObjectMapper objectMapper;

    public JacksonJsonService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        // Auto-discover other modules (e.g. JDK8, Kotlin if present)
        this.objectMapper.findAndRegisterModules();

        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // Fix for "No serializer found for class..." (e.g. LangChain4j ChatResponse)
        this.objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // Optional: Allow access to private fields if no getters exist (makes it more "easy")
        // this.objectMapper.setVisibility(com.fasterxml.jackson.annotation.PropertyAccessor.FIELD, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY);
    }

    public JacksonJsonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object to JSON", e);
            throw new JsonException("Error serializing object to JSON", e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON to object", e);
            throw new JsonException("Error deserializing JSON to object", e);
        }
    }

    @Override
    public <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON to List", e);
            throw new JsonException("Error deserializing JSON to List", e);
        }
    }

    @Override
    public Map<String, Object> fromJsonToMap(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON to Map", e);
            throw new JsonException("Error deserializing JSON to Map", e);
        }
    }
}
