package com.test.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object source) {
        String result = "";
        if (source != null) {
            try {
                return objectMapper.writeValueAsString(source);
            } catch (JsonProcessingException e) {
                LogUtil.error(JsonUtil.class, String.format("Json serializes failed, object type: %s, exception: %s",
                        source.getClass().getName(), e.toString()));
            }
        }
        return result;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        T result = null;
        try {
            result = objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            LogUtil.error(JsonUtil.class,
                    String.format("Json deserializes failed, json string: \"%s\", class: %s", json, clazz), e);
        }
        return result;
    }

    public static <T> T fromJson(String json, Class<T> clazz, Class<?> genericType) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(clazz, genericType);
        T result = null;
        try {
            result = objectMapper.readValue(json, type);
        } catch (Exception e) {
            LogUtil.error(JsonUtil.class,
                    String.format("Json deserializes failed, json string: \"%s\",class: %s", json, clazz), e);
        }
        return result;
    }
}
