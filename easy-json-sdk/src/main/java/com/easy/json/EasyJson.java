package com.easy.json;


import com.easy.json.core.JsonService;
import com.easy.json.impl.JacksonJsonService;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Static Utility for Easy JSON operations.
 * Delegates to the configured JsonService.
 */
public class EasyJson {

    @Setter
    private static volatile JsonService service;

    private EasyJson() {
        // Private constructor
    }

    private static JsonService getService() {
        if (service == null) {
            synchronized (EasyJson.class) {
                if (service == null) {
                    // Fallback to Jackson if not initialized
                    service = new JacksonJsonService();
                }
            }
        }
        return service;
    }

    public static String toJson(Object object) {
        return getService().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return getService().fromJson(json, clazz);
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        return getService().fromJsonToList(json, clazz);
    }

    public static Map<String, Object> fromJsonToMap(String json) {
        return getService().fromJsonToMap(json);
    }
}
