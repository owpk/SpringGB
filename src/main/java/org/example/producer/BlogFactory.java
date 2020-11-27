package org.example.producer;

import java.util.*;

public class BlogFactory {
    private static final Random RANDOM = new Random();
    private static final Map<String, List<String>> THEMES;
    static {
        THEMES = new HashMap<>();
        THEMES.put("java", new ArrayList<>(List.of("JavaMsg1", "JavaMsg2", "JavaMsg3")));
        THEMES.put("php", new ArrayList<>(List.of("PhpMsg1", "PhpMsg2", "PhpMsg3")));
        THEMES.put("js", new ArrayList<>(List.of("JsMsg1", "JsMsg2", "JsMsg3")));
    }

    public static String getBlog(String theme) {
        List<String> list = THEMES.get(theme);
        return list.get(RANDOM.nextInt(list.size()));
    }

}
