package com.easy.json.core;

import java.util.List;
import java.util.Map;

/**
 * Core JSON Service Interface.
 */
public interface JsonService {
    String toJson(Object object);

    <T> T fromJson(String json, Class<T> clazz);

    <T> List<T> fromJsonToList(String json, Class<T> clazz);

    Map<String, Object> fromJsonToMap(String json);
}
