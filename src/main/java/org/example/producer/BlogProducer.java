package org.example.producer;

import java.util.Random;

public class BlogProducer {
    private static final String[] THEMES = {"java", "js", "php"};

    public static void main(String[] argv) throws Exception {
        Producer producer = new Producer("newsIt");
        Random r = new Random();

        for (int i = 0; i < 15; i++) {
            String theme = THEMES[r.nextInt(THEMES.length)];
            producer.produce(theme, BlogFactory.getBlog(theme));
        }
    }


}
