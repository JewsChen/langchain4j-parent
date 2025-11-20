package com.easy.json.impl;

import com.easy.json.core.JsonService;
import com.easy.json.exception.JsonException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class GsonJsonService implements JsonService {

    private final Gson gson;

    public GsonJsonService() {
        this.gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .serializeNulls()
                .create();
    }

    public GsonJsonService(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJson(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            log.error("Error serializing object to JSON with Gson", e);
            throw new JsonException("Error serializing object to JSON with Gson", e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            log.error("Error deserializing JSON to object with Gson", e);
            throw new JsonException("Error deserializing JSON to object with Gson", e);
        }
    }

    @Override
    public <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        try {
            Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
            return gson.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            log.error("Error deserializing JSON to List with Gson", e);
            throw new JsonException("Error deserializing JSON to List with Gson", e);
        }
    }

    @Override
    public Map<String, Object> fromJsonToMap(String json) {
        try {
            Type mapType = new TypeToken<Map<String, Object>>() {
            }.getType();
            return gson.fromJson(json, mapType);
        } catch (JsonSyntaxException e) {
            log.error("Error deserializing JSON to Map with Gson", e);
            throw new JsonException("Error deserializing JSON to Map with Gson", e);
        }
    }
}
