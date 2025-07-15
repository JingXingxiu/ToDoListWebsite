package com.javaweb.todolist.util;

import java.util.UUID;

public class GenerationUtil {
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
