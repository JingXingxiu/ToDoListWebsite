/// 模板，jackson解析json或生成json！
package com.javaweb.todolist.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JacksonUtil {
    // 1. 全局单例ObjectMapper（线程安全）
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()); // 支持LocalDateTime等Java8时间类

    // 2. 对象 → JSON字符串
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("对象转JSON失败", e);
        }
    }

    // 3. JSON字符串 → 对象
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON解析失败", e);
        }
    }

    // 4. 从HttpServletRequest读取JSON并解析
    public static <T> T parseRequest(HttpServletRequest req, Class<T> clazz) throws IOException {
        return objectMapper.readValue(req.getInputStream(), clazz);
    }

    // 5. 向HttpServletResponse写入JSON响应
    public static void writeJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(resp.getWriter(), obj);
    }
}